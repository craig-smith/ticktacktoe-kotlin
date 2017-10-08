package com.craig.kotlin.ticktacktoe.game

import com.craig.kotlin.ticktacktoe.game.data.Play
import com.craig.kotlin.ticktacktoe.game.data.Player

interface TickTackToeService {
    fun setComputerMove(gameBoardDTO: GameBoardDTO, player: Player): Play
    fun setPlayerMove(gameBoardDTO: GameBoardDTO, play: Play)
    fun calculateWinner(gameBoardDTO: GameBoardDTO)

}