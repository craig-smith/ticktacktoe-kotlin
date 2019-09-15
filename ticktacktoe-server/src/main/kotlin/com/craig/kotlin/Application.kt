package com.craig.kotlin

import com.craig.kotlin.ticktacktoe.game.GameBoardService
import com.craig.kotlin.ticktacktoe.game.TickTackToeService
import com.craig.kotlin.ticktacktoe.game.TickTackToeServiceBean
import com.craig.kotlin.ticktacktoe.game.data.GameBoardServiceImpl
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = arrayOf("com.craig.kotlin.ticktacktoe.game.data"))
@EntityScan(basePackages = arrayOf("com.craig.kotlin.ticktacktoe.game.data"))
@ComponentScan(basePackages = arrayOf("com.craig.kotlin"))
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

@Bean
fun getTickTackToeService(): TickTackToeService {
    return TickTackToeServiceBean()
}

@Bean
fun getGameBoardService(): GameBoardService {
    return GameBoardServiceImpl()
}




