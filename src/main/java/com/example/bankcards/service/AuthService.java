package com.example.bankcards.service;

import com.example.bankcards.dto.AuthResponse;
import com.example.bankcards.dto.ChangePasswordRequest;
import com.example.bankcards.dto.user.UserLoginRequest;
import com.example.bankcards.dto.user.UserDto;
import com.example.bankcards.dto.user.UserRegistrationRequest;

import java.util.Optional;

public interface AuthService {

    UserDto register(UserRegistrationRequest request);

    AuthResponse authenticate(UserLoginRequest request);

    AuthResponse refreshToken(String refreshToken);

    void revokeRefreshToken(String refreshToken);

    void changePassword(Long userId, ChangePasswordRequest request);

    Optional<UserDto> findUserByUsername(String username);
}
