package dev.vstd.beshoppingcart.repository

import dev.vstd.beshoppingcart.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<ProductEntity, Long>