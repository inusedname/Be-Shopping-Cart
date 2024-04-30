package dev.vstd.beshoppingcart.dto

import dev.vstd.beshoppingcart.entity.OrderEntity

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