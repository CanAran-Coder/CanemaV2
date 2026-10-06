package org.test.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.test.backend.dto.LoginRequest;
import org.test.backend.dto.RegisterRequestDTO;
import org.test.backend.entity.User;
import org.test.backend.enums.Role;
import org.test.backend.repository.UserRepository;
import org.test.backend.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loginReturnsTokenWhenCredentialsMatch() {
        User user = User.builder()
                .email("user@test.com")
                .password("hash")
                .role(Role.ROLE_USER)
                .build();
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("secret", "hash")).thenReturn(true);
        when(jwtService.generateToken("user@test.com")).thenReturn("token");

        String token = userService.login(new LoginRequest("user@test.com", "secret"));

        assertEquals("token", token);
    }

    @Test
    void loginRejectsWrongPassword() {
        User user = User.builder()
                .email("user@test.com")
                .password("hash")
                .role(Role.ROLE_USER)
                .build();
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "hash")).thenReturn(false);

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> userService.login(new LoginRequest("user@test.com", "wrong"))
        );

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void registerSavesEncodedPassword() {
        when(userRepository.existsByEmail("user@test.com")).thenReturn(false);
        when(passwordEncoder.encode("secret")).thenReturn("hash");

        userService.register(new RegisterRequestDTO("user@test.com", "secret"));

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertEquals("user@test.com", captor.getValue().getEmail());
        assertEquals("hash", captor.getValue().getPassword());
        assertEquals(Role.ROLE_USER, captor.getValue().getRole());
    }

    @Test
    void registerRejectsExistingEmail() {
        when(userRepository.existsByEmail("user@test.com")).thenReturn(true);

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> userService.register(new RegisterRequestDTO("user@test.com", "secret"))
        );

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        verify(userRepository, never()).save(any());
    }
}
