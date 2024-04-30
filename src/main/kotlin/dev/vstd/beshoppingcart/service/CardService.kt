package dev.vstd.beshoppingcart.service

import dev.vstd.beshoppingcart.entity.CardEntity
import dev.vstd.beshoppingcart.entity.UserEntity
import dev.vstd.beshoppingcart.repository.CardRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class CardService(private val cardRepository: CardRepository) {

    fun createCard(userEntity: UserEntity): CardEntity {
        return cardRepository.save(
            CardEntity(
                user = userEntity,
                cardNumber = Random.nextLong(1000000000000000, 9999999999999999).toString(),
                cvv = Random.nextInt(100, 999).toString(),
                expirationDate = "12/27",
                balance = 0
            )
        )
    }

    fun findCardByUserId(userId: Long): CardEntity? {
        return cardRepository.findFirstByUserId(userId)
    }
}