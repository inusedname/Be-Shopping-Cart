package dev.vstd.beshoppingcart.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Login body")
class LoginBodyDto(
    val email: String,
    val password: String
)