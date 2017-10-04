package com.craig.kotlin.ticktacktoe.game

import java.util.*

class GameBoardDTO(val id: Long, moves: MutableSet<Play>, var gameOver: Boolean, var winner: Optional<Player>) {
    val boardSet: MutableSet<Play> = moves

    fun setMove(play: Play){
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