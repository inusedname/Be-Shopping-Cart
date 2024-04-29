package dev.vstd.beshoppingcart.repository

import dev.vstd.beshoppingcart.entity.ProductsOfOrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductsOfOrderRepository: JpaRepository<ProductsOfOrderEntity, Long>