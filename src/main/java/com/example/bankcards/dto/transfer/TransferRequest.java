package com.example.bankcards.dto.transfer;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferRequest {

    private UUID fromCardId;
    private UUID toCardId;
    private BigDecimal amount;
}
