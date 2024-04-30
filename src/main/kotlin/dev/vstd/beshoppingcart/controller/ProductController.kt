package dev.vstd.beshoppingcart.controller

import dev.vstd.beshoppingcart.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
@RequestMapping("/product")
class ProductController(private val productService: ProductService) {
    private val logger = Logger.getLogger(ProductController::class.java.name)

    @GetMapping("/all")
    fun getAllClothes(): ResponseEntity<*> {
        return ResponseEntity.ok(productService.getAllClothes())
    }
}