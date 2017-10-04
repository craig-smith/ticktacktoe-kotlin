package com.craig.kotlin.ticktacktoe.game

interface TickTackToeService {
    fun setComputerMove(gameBoardDTO: GameBoardDTO, player: Player): Play
    fun setPlayerMove(gameBoardDTO: GameBoardDTO, play: Play)
    fun calculateWinner(gameBoardDTO: GameBoardDTO)

}