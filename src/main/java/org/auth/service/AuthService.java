package org.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.auth.common.dto.request.LoginRequest;
import org.auth.common.dto.response.LoginResponse;
import org.auth.common.exception.CustomException;
import org.auth.persistence.entity.UserEntity;

@ApplicationScoped
public class AuthService {

    private final TokenService tokenService;
    private final UserService userService;

    public AuthService(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    public LoginResponse login(LoginRequest request, String traceId) {
        UserEntity user = this.userService.getByUsernameAndPassword(request);
        if(user != null) {
            String token = this.tokenService.generate(user, traceId);
            return new LoginResponse();
        }else{
            throw new CustomException("001", "User not found", traceId);
        }

    }

    public void logout(String username, String traceId) {

    }

    public LoginResponse refresh(String token, String traceId) {
        String newToken = this.tokenService.refresh(token, traceId);
        return new LoginResponse();
    }
}
