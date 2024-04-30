package dev.vstd.beshoppingcart.controller

import dev.vstd.beshoppingcart.dto.LoginBodyDto
import dev.vstd.beshoppingcart.dto.LoginResponseDto
import dev.vstd.beshoppingcart.dto.SignupBodyDto
import dev.vstd.beshoppingcart.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
@RequestMapping("/auth")
class AuthController(private val userService: UserService) {
    private val logger = Logger.getLogger(this::class.qualifiedName)

    @PostMapping("/login")
    fun postLogin(@RequestBody body: LoginBodyDto): ResponseEntity<*> {
        val user = userService.findUserByEmail(body.email)
        if (user == null) {
            return ResponseEntity.badRequest().body("No account has this email")
        }
        return if (user.password == body.password) {
            ResponseEntity.ok(LoginResponseDto.fromUserEntity(user))
        } else {
            ResponseEntity.badRequest().body("Password is incorrect")
        }
    }

    @PostMapping("/signup")
    fun postSignup(@RequestBody body: SignupBodyDto): ResponseEntity<*> {
        val user = userService.findUserByEmail(body.email)
        return if (user != null) {
            ResponseEntity.badRequest().body("This email has been used")
        } else {
            val newUser = userService.createUser(body.email, password = body.password, username = body.username)
            return ResponseEntity.ok(LoginResponseDto.fromUserEntity(newUser))
        }
    }
}