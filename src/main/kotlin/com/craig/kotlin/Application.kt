package com.craig.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = arrayOf("com.craig.kotlin"))
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

@Bean
fun getTickTackToeService(): TickTackToeService {
    return TickTackToeServiceBean()
}




