package dev.vstd.beshoppingcart.dto

import dev.vstd.beshoppingcart.entity.OrderEntity
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Create order body")
data class CreateOrderBodyDto(
    val products: List<ProductOfOrderDto>,
    val address: String,
    val purchaseMethod: OrderEntity.PurchaseMethod,
    val purchaseMethodId: Long?
) {
    class ProductOfOrderDto(
        val productId: Long,
        val quantity: Int,
    )
}