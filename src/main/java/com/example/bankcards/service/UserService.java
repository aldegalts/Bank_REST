package com.example.bankcards.service;

import com.example.bankcards.dto.user.UserDto;
import com.example.bankcards.dto.user.UserRegistrationRequest;
import com.example.bankcards.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    UserDto createUser(UserRegistrationRequest request);

    Optional<UserEntity> findByUsername(String username);

    UserDto getById(Long id);

    UserDto assignRoles(Long userId, Set<String> roles);

    void updatePassword(Long userId, String rawPassword);

    UserDto toggleEnabled(Long userId, boolean enabled);

    void deleteUser(Long userId);

    Page<UserDto> searchUsers(String filter, Pageable pageable);
}
