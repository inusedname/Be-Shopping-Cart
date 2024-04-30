package dev.vstd.beshoppingcart.controller

import dev.vstd.beshoppingcart.dto.CreateOrderBodyDto
import dev.vstd.beshoppingcart.entity.OrderEntity
import dev.vstd.beshoppingcart.service.OrderService
import dev.vstd.beshoppingcart.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Date
import java.time.LocalDate

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService,
    private val userService: UserService
) {
    @GetMapping("/all")
    fun allOrders(): ResponseEntity<*> {
        return ResponseEntity.ok(orderService.findAll())
    }

    @PostMapping("/create")
    fun createOrder(@RequestParam userId: Long, @RequestBody body: CreateOrderBodyDto): ResponseEntity<*> {
        val user = userService.findUserById(userId)
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found")
        }
        return try {
            val saved = orderService.createOrder(user, body)
            ResponseEntity.ok(saved)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}