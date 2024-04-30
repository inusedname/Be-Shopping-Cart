package dev.vstd.beshoppingcart.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Signup body")
class SignupBodyDto(
    val username: String,
    val email: String,
    val password: String,
)