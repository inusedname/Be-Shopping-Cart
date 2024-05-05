package dev.vstd.beshoppingcart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableTransactionManagement
@SpringBootApplication
class BeShoppingCartApplication {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object: WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<BeShoppingCartApplication>(*args)
}
