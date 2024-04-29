package dev.vstd.beshoppingcart.controller

import dev.vstd.beshoppingcart.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
) {
    @GetMapping("/all")
    fun allOrders(): ResponseEntity<*> {
        return ResponseEntity.ok(orderService.findAll())
    }
}