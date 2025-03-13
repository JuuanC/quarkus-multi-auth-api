package org.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.auth.common.dto.request.LoginRequest;
import org.auth.common.dto.response.LoginResponse;

@ApplicationScoped
public class AuthService {

    private final TokenService tokenService;

    public AuthService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public LoginResponse login(LoginRequest request, String traceId) {
        return new LoginResponse();
    }

    public void logout(String username, String traceId) {

    }

    public LoginResponse refresh(String token, String traceId) {
        return new LoginResponse();
    }
}
