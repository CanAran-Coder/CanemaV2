package org.test.backend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.test.backend.dto.LoginRequest;
import org.test.backend.dto.MeResponse;
import org.test.backend.dto.RegisterRequestDTO;
import org.test.backend.entity.User;
import org.test.backend.enums.Role;
import org.test.backend.repository.UserRepository;
import org.test.backend.service.JWTService;
import org.test.backend.service.UserService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .filter(found -> passwordEncoder.matches(request.password(), found.getPassword()))
                .orElseThrow(() -> {
                    log.warn("Failed login attempt for {}", request.email());
                    return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
                });

        log.info("User logged in: {}", user.getEmail());
        return jwtService.generateToken(user.getEmail());
    }

    @Override
    public void register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.email())) {
            log.warn("Registration rejected, email already exists: {}", request.email());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        log.info("User registered: {}", user.getEmail());
    }

    @Override
    public MeResponse me(String accessToken) {
        if (jwtService.isTokenExpired(accessToken) || !jwtService.isTokenValid(accessToken)) {
            return new MeResponse(null,null, null);
        }

        String email = jwtService.extractEmail(accessToken);

        Optional<User> optUser = userRepository.findByEmail(email);
        if (!optUser.isPresent()) {
            return new MeResponse(null,null, null);
        }

        User user = optUser.get();
        return new MeResponse(user.getId(),user.getEmail(), user.getRole());
    }
}
