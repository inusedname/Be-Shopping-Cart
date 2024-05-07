package dev.vstd.beshoppingcart.service

import dev.vstd.beshoppingcart.entity.CardEntity
import dev.vstd.beshoppingcart.entity.CardTransactionEntity
import dev.vstd.beshoppingcart.entity.UserEntity
import dev.vstd.beshoppingcart.repository.CardRepository
import dev.vstd.beshoppingcart.repository.CardTransactionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class CardService(
    private val cardRepository: CardRepository,
    private val cardTransactionRepository: CardTransactionRepository
) {

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

    @Transactional
    fun chargeCard(cardEntity: CardEntity, amount: Long, message: String) {
        cardTransactionRepository.save(
            CardTransactionEntity(
                cardEntity = cardEntity,
                amount = amount,
                description = message,
                date = LocalDateTime.now()
            )
        )
        cardEntity.balance -= amount
        cardRepository.save(cardEntity)
    }

    @Transactional
    fun topUpCard(cardEntity: CardEntity, amount: Long, message: String) {
        cardTransactionRepository.save(
            CardTransactionEntity(
                cardEntity = cardEntity,
                amount = amount,
                description = message,
                date = LocalDateTime.now()
            )
        )
        cardEntity.balance += amount
        cardRepository.save(cardEntity)
    }

    fun findCardByUserId(userId: Long): CardEntity? {
        return cardRepository.findFirstByUserId(userId)
    }
}