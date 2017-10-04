package com.craig.kotlin.ticktacktoe.game.data

import com.craig.kotlin.ticktacktoe.game.GameBoardDTO
import com.craig.kotlin.ticktacktoe.game.Play
import com.craig.kotlin.ticktacktoe.game.Player
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "GAMEBOARD")
internal data class GameBoardEntity(
        val id: Long? = null,
        val gameBoard: MutableSet<Play>,
        val gameOver: Boolean,
        val winner: Optional<Player>) {

    private constructor() : this(gameBoard = mutableSetOf(), gameOver = false, winner = Optional.empty())

    fun toDTO() : GameBoardDTO = GameBoardDTO(id!!, gameBoard, gameOver, winner)

    companion object {
        fun fromDTO(gameBoardDTO: GameBoardDTO)
                = GameBoardEntity(gameBoardDTO.id, gameBoardDTO.boardSet, gameBoardDTO.isGameOver(), gameBoardDTO.winner)

    }
}

