package dev.vstd.beshoppingcart.service

import dev.vstd.beshoppingcart.dto.CreateOrderBodyDto
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
    private val productService: ProductService
) {
    fun findAll(): List<OrderEntity> {
        return orderRepository.findAll()
    }

    fun findByOrderId(orderId: Long): OrderEntity {
        return orderRepository.findById(orderId).get()
    }

    fun createOrder(
        user: UserEntity,
        body: CreateOrderBodyDto,
    ): OrderEntity {
        // Create order
        val allProducts = productService.getAllClothes()
        val newOrder = OrderEntity(
            user = user,
            date = Date.valueOf(LocalDate.now()),
            shippingAddress = body.shipment.toString(),
            purchaseMethod = body.purchaseMethod,
            purchaseMethodId = body.purchaseMethodId
        )
        val savedOrder = orderRepository.save(newOrder)
        val newProducts = body.products.map { dto ->
            val product = allProducts.find { it.id == dto.productId }
            if (product == null) {
                throw IllegalArgumentException("Product with id ${dto.productId} not found")
            }
            ProductsOfOrderEntity(
                productEntity = product,
                orderEntity = savedOrder,
                quantity = dto.quantity,
                snapshotPrice = product.price
            )
        }
        productsOfOrderRepository.saveAll(newProducts)

        return savedOrder
    }
}