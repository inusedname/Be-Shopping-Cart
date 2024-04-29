package dev.vstd.beshoppingcart.repository

import dev.vstd.beshoppingcart.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun existsByEmailAndPassword(email: String, password: String): Boolean
}