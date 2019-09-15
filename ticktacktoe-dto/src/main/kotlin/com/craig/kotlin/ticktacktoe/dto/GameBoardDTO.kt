package com.craig.kotlin.ticktacktoe.game

import com.craig.kotlin.ticktacktoe.game.data.PlayDTO
import java.util.*

class GameBoardDTO(val id: Long, moves: MutableSet<PlayDTO>, var gameOver: Boolean, var winner: Optional<Player>) {
    val boardSet: MutableSet<PlayDTO> = moves

    fun setMove(play: PlayDTO){
        boardSet.add(play)
    }

    fun setGameOver(player: Optional<Player>) {
        gameOver = true
        winner = player
    }

    fun isGameOver(): Boolean {
        return gameOver
    }



}