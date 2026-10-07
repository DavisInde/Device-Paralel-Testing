package org.example.test

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
object MainApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(MainApplication::class.java, *args)
    }
}
