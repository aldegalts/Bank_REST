package com.example.bankcards.repository;

import com.example.bankcards.entity.TransferEntity;
import com.example.bankcards.entity.CardEntity;
import com.example.bankcards.enums.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, UUID> {

    List<TransferEntity> findByFromCardOrToCard(CardEntity fromCard, CardEntity toCard);

    List<TransferEntity> findByStatus(TransferStatus status);
}
