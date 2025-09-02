package com.example.bankcards.dto.transfer;

import com.example.bankcards.enums.TransferStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDto {

    private UUID id;
    private UUID fromCardId;
    private UUID toCardId;
    private BigDecimal amount;
    private TransferStatus status;
    private String reason;
    private LocalDateTime createdAt;
}
