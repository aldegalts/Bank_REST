package com.example.bankcards.service;

import com.example.bankcards.entity.RefreshTokenEntity;
import com.example.bankcards.entity.UserEntity;
import com.example.bankcards.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshTokenEntity createToken(UserEntity user, long validityInSeconds) {
        refreshTokenRepository.deleteByUser(user);

        RefreshTokenEntity token = RefreshTokenEntity.builder()
            .user(user)
            .token(UUID.randomUUID().toString())
            .expiryDate(LocalDateTime.now().plusSeconds(validityInSeconds))
            .build();

        return refreshTokenRepository.save(token);
    }

    @Transactional(readOnly = true)
    public Optional<RefreshTokenEntity> validateToken(String token) {
        return refreshTokenRepository.findByToken(token)
            .filter(rt -> rt.getExpiryDate().isAfter(LocalDateTime.now()));
    }

    @Transactional
    public void revokeToken(String token) {
        refreshTokenRepository.findByToken(token)
            .ifPresent(refreshTokenRepository::delete);
    }

    @Transactional
    public void revokeUserTokens(UserEntity user) {
        refreshTokenRepository.deleteByUser(user);
    }
}
