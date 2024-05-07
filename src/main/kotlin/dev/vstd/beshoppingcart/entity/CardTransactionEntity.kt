package dev.vstd.beshoppingcart.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class CardTransactionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne @JoinColumn(name = "card_id")
    val cardEntity: CardEntity,
    val date: LocalDateTime,
    val amount: Long,
    val description: String,
)