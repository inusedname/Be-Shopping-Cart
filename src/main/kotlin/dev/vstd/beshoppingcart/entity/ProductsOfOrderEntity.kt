package dev.vstd.beshoppingcart.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    var orderEntity: OrderEntity,

    val quantity: Int,

    val snapshotPrice: Long,
)