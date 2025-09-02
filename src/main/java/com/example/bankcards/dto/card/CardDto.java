package com.example.bankcards.dto.card;

import com.example.bankcards.enums.CardStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {

    private UUID id;
    private String last4;
    private int expiryMonth;
    private int expiryYear;
    private CardStatus status;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
