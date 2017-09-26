package com.craig.kotlin.controller

import com.craig.kotlin.TickTackToeService
import com.craig.kotlin.data.Game
import com.craig.kotlin.data.Message
import com.craig.kotlin.data.Player
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@RestController
@EnableWebMvc
class JSONController {

    @Autowired
    private lateinit var tickTackToeService: TickTackToeService

    @GetMapping("/hello")
    fun helloKotlin(): Message {
        return Message("hello")
    }


    @PostMapping("/ticktacktoe/play", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getTickTackTo(@RequestBody game: Game): Game {
        tickTackToeService.setPlayerMove(game.gameBoard, game.play)

        var winner = tickTackToeService.getWinner(game.gameBoard)
        if (winner.isPresent) {
            game.gameBoard.setGameOver(winner.get())
        } else {
            if (game.play.player.equals(Player.O)) tickTackToeService.setComputerMove(game.gameBoard, Player.X)
            else tickTackToeService.setComputerMove(game.gameBoard, Player.O)
        }
        winner = tickTackToeService.getWinner(game.gameBoard)
        if (winner.isPresent) {
            game.gameBoard.setGameOver(winner.get())
        }
        return game

    }

}

