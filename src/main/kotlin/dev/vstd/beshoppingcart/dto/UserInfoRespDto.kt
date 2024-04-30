package dev.vstd.beshoppingcart.dto

import dev.vstd.beshoppingcart.entity.UserEntity
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "User info response")
data class UserInfoRespDto(
    val username: String,
    val email: String,
    val address: String?,
) {
    companion object {
        fun fromUserEntity(userEntity: UserEntity): UserInfoRespDto {
            return UserInfoRespDto(
                username = userEntity.username,
                email = userEntity.email,
                address = userEntity.address,
            )
        }
    }
}