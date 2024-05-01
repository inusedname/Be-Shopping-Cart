package dev.vstd.beshoppingcart.repository

import dev.vstd.beshoppingcart.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderEntity, Long> {
    fun findAllByUserId(userId: Long): List<OrderEntity>
}