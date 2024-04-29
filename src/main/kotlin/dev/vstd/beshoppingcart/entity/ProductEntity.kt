package dev.vstd.beshoppingcart.entity

import jakarta.persistence.*

@Entity
class ProductEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val price: Long,
    val previewImage: String,
    val description: String,
    val seller: String,
)