package dev.vstd.beshoppingcart.entity

import jakarta.persistence.*
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class OrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne @JoinColumn(name = "user_id")
    val user: UserEntity,
    val date: LocalDateTime,
    val shippingAddress: String,
    @Enumerated(EnumType.STRING)
    val purchaseMethod: PurchaseMethod,
    val status: Status = Status.PENDING,
    val purchaseMethodId: Long?,
    @OneToMany(mappedBy = "orderEntity")
    val products: List<ProductsOfOrderEntity>? = null,
) {
    enum class PurchaseMethod {
        COD,
        CARD,
        OTHER,
    }
    enum class Status {
        PENDING,
        SHIPPED,
        DELIVERED,
        CANCELLED,
    }
}