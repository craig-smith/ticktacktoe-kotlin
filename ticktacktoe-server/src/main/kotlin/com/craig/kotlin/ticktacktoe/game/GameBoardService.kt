package com.craig.kotlin.ticktacktoe.game

import java.util.*

interface GameBoardService {
    fun getById(id: Long): Optional<GameBoardDTO>
    fun save(gameBoardDTO: GameBoardDTO)
    fun newGame(): GameBoardDTO
}