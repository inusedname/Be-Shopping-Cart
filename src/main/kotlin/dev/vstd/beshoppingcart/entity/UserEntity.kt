package dev.vstd.beshoppingcart.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne

@Entity
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String,
    val email: String,
    val password: String,
    val role: Role,
    var address: String? = null,

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    val card: CardEntity? = null
) {
    enum class Role {
        ADMIN, USER
    }
}