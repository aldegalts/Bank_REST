package com.example.bankcards.entity;

import com.example.bankcards.enums.CardStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cards", uniqueConstraints = {
        @UniqueConstraint(name = "uq_cards_fingerprint", columnNames = "card_number_fingerprint")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    @Column(nullable = false)
    private String cardNumberEnc;

    @Column(nullable = false, length = 64)
    private String cardNumberFingerprint;

    @Column(nullable = false, length = 4)
    private String last4;

    @Column(nullable = false)
    @Min(1)
    @Max(12)
    private Integer expiryMonth;

    @Column(nullable = false)
    private Integer expiryYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardStatus status;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Version
    private Integer version = 0;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
