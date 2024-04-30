package dev.vstd.beshoppingcart.repository

import dev.vstd.beshoppingcart.entity.CardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository: JpaRepository<CardEntity, Long> {
    fun findFirstByUserId(userId: Long): CardEntity?
}