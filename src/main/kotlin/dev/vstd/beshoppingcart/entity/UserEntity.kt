package dev.vstd.beshoppingcart.entity

import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String,
    val email: String,
    val password: String,
    val role: Role,
    var address: String? = null,
) {
    enum class Role {
        ADMIN, USER
    }
}