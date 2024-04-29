package dev.vstd.beshoppingcart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@SpringBootApplication
class BeShoppingCartApplication

fun main(args: Array<String>) {
    runApplication<BeShoppingCartApplication>(*args)
}
