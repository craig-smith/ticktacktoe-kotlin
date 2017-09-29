package com.craig.kotlin

import com.craig.kotlin.data.GameBoard
import com.craig.kotlin.data.Play
import com.craig.kotlin.data.Player
import java.util.*

interface TickTackToeService {
    fun setComputerMove(gameBoard: GameBoard, player: Player)
    fun setPlayerMove(gameBoard: GameBoard, play: Play)
    fun calculateWinner(gameBoard: GameBoard)

}