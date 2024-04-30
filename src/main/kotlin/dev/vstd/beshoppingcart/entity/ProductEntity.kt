package dev.vstd.beshoppingcart.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Schema(description = "Product entity")
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