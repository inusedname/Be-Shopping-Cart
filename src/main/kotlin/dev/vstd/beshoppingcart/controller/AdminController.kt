package dev.vstd.beshoppingcart.controller

import dev.vstd.beshoppingcart.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminController(private val orderService: OrderService) {

    @GetMapping("/order/all")
    fun getAllOrders(): ResponseEntity<*> {
        return ResponseEntity.ok(orderService.findAll())
    }

    @PostMapping("/order/status")
    fun updateStatus(@RequestParam orderId: Long, @RequestParam status: String): ResponseEntity<*> {
        return try {
            orderService.updateStatus(orderId, status)
            ResponseEntity.ok("Status updated")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}