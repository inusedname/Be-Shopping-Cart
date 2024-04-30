package dev.vstd.beshoppingcart.controller

import dev.vstd.beshoppingcart.dto.UserInfoRespDto
import dev.vstd.beshoppingcart.service.CardService
import dev.vstd.beshoppingcart.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/user")
@RestController
class UserController(private val userService: UserService, private val cardService: CardService) {

    @GetMapping("/detail")
    fun getUserInfo(@RequestParam userId: Long): ResponseEntity<*> {
        val user = userService.findUserById(userId)
        return if (user == null) {
            ResponseEntity.badRequest().body("No account has this id")
        } else {
            ResponseEntity.ok(UserInfoRespDto.fromUserEntity(user))
        }
    }

    @PutMapping("/address")
    fun updateAddress(@RequestParam userId: Long, @RequestParam address: String): ResponseEntity<String> {
        val user = userService.findUserById(userId)
        return if (user == null) {
            ResponseEntity.badRequest().body("No account has this id")
        } else {
            userService.updateAddress(user, address)
            ResponseEntity.ok("Success!")
        }
    }

    @PostMapping("/card")
    fun registerCard(@RequestParam userId: Long): ResponseEntity<*> {
        val user = userService.findUserById(userId)
        return if (user == null) {
            ResponseEntity.badRequest().body("No account has this id")
        } else {
            val card = cardService.findCardByUserId(userId)
            if (card != null) {
                return ResponseEntity.badRequest().body("This account already has a card")
            } else {
                val newCard = cardService.createCard(user)
                return ResponseEntity.ok(newCard)
            }
        }
    }

    @GetMapping("/card")
    fun getCardInfo(@RequestParam userId: Long): ResponseEntity<*> {
        val user = userService.findUserById(userId)
        return if (user == null) {
            ResponseEntity.badRequest().body("No account has this id")
        } else {
            val card = cardService.findCardByUserId(userId)
            if (card == null) {
                return ResponseEntity.badRequest().body("This account has no card")
            } else {
                return ResponseEntity.ok(card)
            }
        }
    }

    @PostMapping("validate-cvv")
    fun validateCvv(@RequestParam userId: Long, @RequestParam cvv: String): ResponseEntity<*> {
        val user = userService.findUserById(userId)
        return if (user == null) {
            ResponseEntity.badRequest().body("No account has this id")
        } else {
            val card = cardService.findCardByUserId(userId)
            if (card == null) {
                return ResponseEntity.badRequest().body("This account has no card")
            } else {
                return if (card.cvv == cvv) {
                    ResponseEntity.ok("CVV is correct")
                } else {
                    ResponseEntity.badRequest().body("CVV is incorrect")
                }
            }
        }
    }
}