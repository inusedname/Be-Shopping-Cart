package dev.vstd.beshoppingcart.service

import dev.vstd.beshoppingcart.entity.UserEntity
import dev.vstd.beshoppingcart.entity.OrderEntity
import dev.vstd.beshoppingcart.entity.ProductsOfOrderEntity
import dev.vstd.beshoppingcart.repository.OrderRepository
import dev.vstd.beshoppingcart.repository.ProductsOfOrderRepository
import org.springframework.stereotype.Service
import java.sql.Date
import java.time.LocalDate

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productsOfOrderRepository: ProductsOfOrderRepository,
) {
    fun findAll(): List<OrderEntity> {
        return orderRepository.findAll()
    }

    fun findByOrderId(orderId: Long): OrderEntity {
        return orderRepository.findById(orderId).get()
    }

    fun createOrder(
        user: UserEntity,
        date: LocalDate,
        clothes: List<ProductsOfOrderEntity>
    ): OrderEntity {
        // Create order
        val tmpEntity = OrderEntity(
            user = user,
            date = Date.valueOf(date),
        )
        val savedEntity = orderRepository.save(tmpEntity)
        clothes.forEach {
            it.orderEntity = savedEntity
        }
        productsOfOrderRepository.saveAll(clothes)

        return savedEntity
    }
}