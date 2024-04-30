package dev.vstd.beshoppingcart.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne

@Entity
class CardEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne(mappedBy = "id")
    val user: UserEntity,
    val cardNumber: String,
    val expirationDate: String,
    val cvv: String,
    val balance: Int,
)