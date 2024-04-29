package dev.vstd.beshoppingcart.service

import dev.vstd.beshoppingcart.entity.UserEntity
import dev.vstd.beshoppingcart.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun createUser(email: String, password: String, username: String): UserEntity {
        return userRepository.save(
            UserEntity(
                email = email,
                password = password,
                username = username,
                role = UserEntity.Role.USER
            )
        )
    }

    fun updateAddress(user: UserEntity, address: String): UserEntity? {
        user.address = address
        return userRepository.save(user)
    }

    fun findUserByEmail(email: String): UserEntity? {
        return userRepository.findByEmail(email)
    }

    fun findUserById(id: Long): UserEntity? {
        return userRepository.findById(id).orElse(null)
    }
}