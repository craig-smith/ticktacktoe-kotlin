package com.craig.kotlin.controller

import com.craig.kotlin.TickTackToeService
import com.craig.kotlin.data.GameBoard
import com.craig.kotlin.data.Message
import com.craig.kotlin.data.Play
import com.craig.kotlin.data.Player
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

    @GetMapping("/ticktacktoe")
    fun getTickTackTo(@RequestParam gameBoard: String,
                      @RequestParam player: String, @RequestParam play: String): GameBoard {
        /*tickTackToeService.setPlayerMove(gameBoard, play)
        if (player.equals(Player.O)) tickTackToeService.setComputerMove(gameBoard, Player.X)
        else tickTackToeService.setComputerMove(gameBoard, Player.X)

        val winner = tickTackToeService.getWinner(gameBoard)
        if (winner.isPresent) {
            gameBoard.setGameOver(winner.get())
        }*/
        return GameBoard()

    }

}

