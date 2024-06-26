package dev.vstd.beshoppingcart.service

import dev.vstd.beshoppingcart.dto.CreateOrderBodyDto
import dev.vstd.beshoppingcart.dto.OrderRespDto
import dev.vstd.beshoppingcart.entity.CardEntity
import dev.vstd.beshoppingcart.entity.UserEntity
import dev.vstd.beshoppingcart.entity.OrderEntity
import dev.vstd.beshoppingcart.entity.ProductsOfOrderEntity
import dev.vstd.beshoppingcart.repository.OrderRepository
import dev.vstd.beshoppingcart.repository.ProductsOfOrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productsOfOrderRepository: ProductsOfOrderRepository,
    private val productService: ProductService,
    private val cardService: CardService
) {
    fun findAll(userId: Long): List<OrderRespDto> {
        return orderRepository.findAllByUserId(userId).map { OrderRespDto.fromOrderEntity(it) }
    }

    fun findAll(): List<OrderRespDto> {
        return orderRepository.findAll().map { OrderRespDto.fromOrderEntity(it) }
    }

    fun findByOrderId(orderId: Long): OrderEntity? {
        return orderRepository.findById(orderId).getOrNull()
    }

    @Transactional
    fun updateStatus(
        orderId: Long,
        status: String,
    ) {
        val order = orderRepository.findById(orderId).get()
        if (order == null) {
            throw IllegalArgumentException("Order with id $orderId not found")
        }
        when (status) {
            OrderEntity.Status.PENDING.name -> order.status = OrderEntity.Status.PENDING
            OrderEntity.Status.SHIPPED.name -> order.status = OrderEntity.Status.SHIPPED
            OrderEntity.Status.DELIVERED.name -> order.status = OrderEntity.Status.DELIVERED
            OrderEntity.Status.CANCELLED.name -> order.status = OrderEntity.Status.CANCELLED
            else -> throw IllegalArgumentException("Invalid status: $status")
        }
        orderRepository.save(order)
    }

    @Transactional
    fun createOrder(
        user: UserEntity,
        body: CreateOrderBodyDto,
    ): Long {
        // Create order
        val allProducts = productService.getAllClothes()
        val newOrder = OrderEntity(
            user = user,
            date = LocalDateTime.now(),
            shippingAddress = body.address,
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
        productsOfOrderRepository.saveAllAndFlush(newProducts)
        return savedOrder.id
    }

    @Transactional
    fun payOrder(order: OrderEntity, card: CardEntity) {
        // if enough balance in method
        if (order.purchaseMethod == OrderEntity.PurchaseMethod.CARD) {
            val totalPrice = order.products!!.sumOf { it.snapshotPrice * it.quantity }
            if (card.balance < totalPrice) {
                throw IllegalArgumentException("Not enough balance")
            }
            cardService.chargeCard(card, totalPrice, "Order#{order.id} payment")
            this.updateStatus(order.id, OrderEntity.Status.SHIPPED.name)
        }
    }
}