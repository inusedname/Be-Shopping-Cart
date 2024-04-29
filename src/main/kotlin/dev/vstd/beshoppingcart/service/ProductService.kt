package dev.vstd.beshoppingcart.service

import dev.vstd.beshoppingcart.entity.ProductEntity
import dev.vstd.beshoppingcart.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import kotlin.jvm.optionals.getOrNull

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {

    fun getAllClothes(): List<ProductEntity> {
        return productRepository.findAll()
    }
}