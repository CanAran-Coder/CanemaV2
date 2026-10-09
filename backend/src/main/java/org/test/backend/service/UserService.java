package org.test.backend.service;

import org.test.backend.dto.LoginRequest;
import org.test.backend.dto.MeResponse;
import org.test.backend.dto.RegisterRequestDTO;

public interface UserService {

    String login(LoginRequest request);

    void register(RegisterRequestDTO request);
    MeResponse me(String accessToken);
}
