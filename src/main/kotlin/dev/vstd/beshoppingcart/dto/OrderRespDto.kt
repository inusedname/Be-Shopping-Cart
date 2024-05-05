package dev.vstd.beshoppingcart.dto

import dev.vstd.beshoppingcart.entity.OrderEntity
import dev.vstd.beshoppingcart.entity.ProductsOfOrderEntity
import java.time.LocalDateTime

data class OrderRespDto(
    val id: Long,
    val userId: Long,
    val date: LocalDateTime,
    val shippingAddress: String,
    val status: OrderEntity.Status,
    val purchaseMethod: OrderEntity.PurchaseMethod?,
    val purchaseMethodId: Long?,
    val products: List<ProductsOfOrderEntity>
) {
    companion object {
        fun fromOrderEntity(orderEntity: OrderEntity): OrderRespDto {
            with(orderEntity) {
                return OrderRespDto(
                    id = id,
                    userId = user.id,
                    date = date,
                    shippingAddress = shippingAddress,
                    status = status,
                    purchaseMethod = purchaseMethod,
                    purchaseMethodId = purchaseMethodId,
                    products = products!!
                )
            }
        }
    }
}