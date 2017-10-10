package com.craig.kotlin.controller

import com.craig.kotlin.ticktacktoe.game.*
import com.craig.kotlin.ticktacktoe.game.data.Play
import com.craig.kotlin.ticktacktoe.game.data.PlayDTO
import com.craig.kotlin.ticktacktoe.game.data.Player
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.util.*


@RestController
@EnableWebMvc
class TickTackToeController {

    @Autowired
    private lateinit var tickTackToeService: TickTackToeService

    @Autowired lateinit var gameBoardService: GameBoardService

    @GetMapping("/hello")
    fun helloKotlin(): Message {
        return Message("hello")
    }


    @PostMapping("/ticktacktoe/play", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getTickTackTo(@RequestBody game: Game): Game {
        val gameBoardDTOOptional = gameBoardService.getById(game.gameBoardDTOId)

        if (gameBoardDTOOptional.isPresent && game.play.isPresent) {
            val gameBoardDto = gameBoardDTOOptional.get()
            tickTackToeService.setPlayerMove(gameBoardDto, game.play.get())

            tickTackToeService.calculateWinner(gameBoardDto)
            val play: PlayDTO
            if (gameBoardDto.isGameOver()) {
                return Game(game.gameBoardDTOId, Optional.empty(), Optional.ofNullable(game.play.get().player))
            } else {
                play = if (game.play.get().player.equals(Player.O)) tickTackToeService.setComputerMove(gameBoardDto, Player.X)
                else tickTackToeService.setComputerMove(gameBoardDto, Player.O)
            }
            tickTackToeService.calculateWinner(gameBoardDto)
            val returnGame: Game

            returnGame = if (gameBoardDto.isGameOver()) {
                Game(gameBoardDto.id, Optional.ofNullable(play), Optional.ofNullable(play.player))
            } else {
                Game(gameBoardDto.id, Optional.ofNullable(play), Optional.empty())
            }
            gameBoardService.save(gameBoardDto)
            return returnGame
        } else {

            return Game(0, Optional.empty(), Optional.empty())
        }

    }

    @GetMapping("/ticktacktoe/newGame")
    fun newGame(): Game {

        val gameDTO = gameBoardService.newGame()
        return Game(gameDTO.id, Optional.empty(), Optional.empty())
    }

}

