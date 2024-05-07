package dev.vstd.beshoppingcart.repository

import dev.vstd.beshoppingcart.entity.CardTransactionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardTransactionRepository: JpaRepository<CardTransactionEntity, Long> {
}