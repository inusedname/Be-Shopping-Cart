package dev.vstd.beshoppingcart.dto

import dev.vstd.beshoppingcart.entity.UserEntity
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Login response")
class LoginResponseDto(
    val id: Long,
    val username: String
) {
    companion object {
        fun fromUserEntity(userEntity: UserEntity): LoginResponseDto {
            return LoginResponseDto(userEntity.id, userEntity.username)
        }
    }
}