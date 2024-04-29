package dev.vstd.beshoppingcart.entity

import jakarta.persistence.*

@Entity
class ProductsOfOrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "cloth_id")
    val productEntity: ProductEntity,

    @ManyToOne
    @JoinColumn(name = "order_id")
    var orderEntity: OrderEntity? = null,

    val quantity: Int,

    val snapshotPrice: Long,
)