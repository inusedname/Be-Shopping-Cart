package dev.vstd.beshoppingcart.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Schema(description = "Card")
@Entity
class CardEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne @JoinColumn(name = "user_id")
    @JsonIgnore
    val user: UserEntity,
    val cardNumber: String,
    val expirationDate: String,
    val cvv: String,
    var balance: Long,
)