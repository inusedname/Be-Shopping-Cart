package dev.vstd.beshoppingcart.dto

import dev.vstd.beshoppingcart.entity.UserEntity

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