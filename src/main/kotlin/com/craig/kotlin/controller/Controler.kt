package com.craig.kotlin.controller

import com.craig.kotlin.TickTackToeService
import com.craig.kotlin.data.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.util.*


@RestController
@EnableWebMvc
class Controler {

    @Autowired
    private lateinit var tickTackToeService: TickTackToeService

    @GetMapping("/hello")
    fun helloKotlin(): Message {
        return Message("hello")
    }

    @GetMapping("/ticktacktoe", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getTickTackTo(@RequestBody game: Game): GameBoard {
        tickTackToeService.setPlayerMove(game.gameBoard, game.play)
        if (game.play.player.equals(Player.O)) tickTackToeService.setComputerMove(game.gameBoard, Player.X)
        else tickTackToeService.setComputerMove(game.gameBoard, Player.X)

        val winner = tickTackToeService.getWinner(game.gameBoard)
        if (winner.isPresent) {
            game.gameBoard.setGameOver(winner.get())
        }
        return game.gameBoard

    }

}

