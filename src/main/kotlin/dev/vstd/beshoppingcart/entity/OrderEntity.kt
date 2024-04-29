package dev.vstd.beshoppingcart.entity

import dev.vstd.beshoppingcart.entity.ProductsOfOrderEntity
import dev.vstd.beshoppingcart.entity.UserEntity
import jakarta.persistence.*
import java.sql.Date

/**
 * This is different form the order that normal privileged users make,
 * that this is the order that the shop's admin buy the clothes from the sellers
 */
@Entity
class OrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    val date: Date,

    @OneToMany(mappedBy = "orderEntity")
    val products: List<ProductsOfOrderEntity>? = null,
)