package dev.vstd.beshoppingcart.controller

import dev.vstd.beshoppingcart.dto.CreateOrderBodyDto
import dev.vstd.beshoppingcart.dto.OrderRespDto
import dev.vstd.beshoppingcart.service.OrderService
import dev.vstd.beshoppingcart.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService,
    private val userService: UserService
) {
    @GetMapping("/all")
    fun allOrders(@RequestParam userId: Long): ResponseEntity<List<OrderRespDto>> {
        return ResponseEntity.ok(orderService.findAll(userId))
    }

    @PostMapping("/create")
    fun createOrder(@RequestParam userId: Long, @RequestBody body: CreateOrderBodyDto): ResponseEntity<*> {
        val user = userService.findUserById(userId)
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found")
        }
        return try {
            val id = orderService.createOrder(user, body)
            val order = orderService.findByOrderId(id)
            ResponseEntity.ok(order)
        } catch (e: java.lang.Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}