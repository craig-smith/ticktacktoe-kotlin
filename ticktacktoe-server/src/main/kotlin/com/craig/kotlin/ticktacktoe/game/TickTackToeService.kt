package com.craig.kotlin.ticktacktoe.game

import com.craig.kotlin.ticktacktoe.game.data.PlayDTO
import com.craig.kotlin.ticktacktoe.game.data.Player

interface TickTackToeService {
    fun setComputerMove(gameBoardDTO: GameBoardDTO, player: Player): PlayDTO
    fun setPlayerMove(gameBoardDTO: GameBoardDTO, playDTO: PlayDTO)
    fun calculateWinner(gameBoardDTO: GameBoardDTO)

}