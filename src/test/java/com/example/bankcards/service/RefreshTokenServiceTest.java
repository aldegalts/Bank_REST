package com.example.bankcards.service;

import com.example.bankcards.entity.RefreshTokenEntity;
import com.example.bankcards.entity.UserEntity;
import com.example.bankcards.repository.RefreshTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RefreshTokenServiceTest {

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private RefreshTokenService refreshTokenService;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = UserEntity.builder()
            .id(1L)
            .username("testuser")
            .password("password")
            .build();
    }

    @Test
    void createToken_shouldSaveAndReturnToken() {
        when(refreshTokenRepository.save(any(RefreshTokenEntity.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        RefreshTokenEntity token = refreshTokenService.createToken(user, 3600);

        assertNotNull(token);
        assertNotNull(token.getToken());
        assertEquals(user, token.getUser());
        assertTrue(token.getExpiryDate().isAfter(LocalDateTime.now()));

        verify(refreshTokenRepository, times(1)).deleteByUser(user);
        verify(refreshTokenRepository, times(1)).save(token);
    }

    @Test
    void validateToken_shouldReturnTokenIfValid() {
        RefreshTokenEntity token = RefreshTokenEntity.builder()
            .token("abc123")
            .user(user)
            .expiryDate(LocalDateTime.now().plusMinutes(5))
            .build();

        when(refreshTokenRepository.findByToken("abc123"))
            .thenReturn(Optional.of(token));

        Optional<RefreshTokenEntity> result = refreshTokenService.validateToken("abc123");

        assertTrue(result.isPresent());
        assertEquals("abc123", result.get().getToken());
    }

    @Test
    void validateToken_shouldReturnEmptyIfExpired() {
        RefreshTokenEntity token = RefreshTokenEntity.builder()
            .token("expired")
            .user(user)
            .expiryDate(LocalDateTime.now().minusMinutes(1))
            .build();

        when(refreshTokenRepository.findByToken("expired"))
            .thenReturn(Optional.of(token));

        Optional<RefreshTokenEntity> result = refreshTokenService.validateToken("expired");

        assertFalse(result.isPresent());
    }

    @Test
    void revokeToken_shouldDeleteTokenIfExists() {
        RefreshTokenEntity token = RefreshTokenEntity.builder()
            .token("todelete")
            .user(user)
            .expiryDate(LocalDateTime.now().plusMinutes(5))
            .build();

        when(refreshTokenRepository.findByToken("todelete"))
            .thenReturn(Optional.of(token));

        refreshTokenService.revokeToken("todelete");

        verify(refreshTokenRepository, times(1)).delete(token);
    }

    @Test
    void revokeUserTokens_shouldCallDeleteByUser() {
        refreshTokenService.revokeUserTokens(user);

        verify(refreshTokenRepository, times(1)).deleteByUser(user);
    }
}
