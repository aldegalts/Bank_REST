package com.example.bankcards.dto.card;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCardRequest {

    private int expiryMonth;
    private int expiryYear;
}
