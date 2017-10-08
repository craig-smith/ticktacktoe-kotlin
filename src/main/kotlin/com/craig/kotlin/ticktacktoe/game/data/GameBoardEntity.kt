package com.craig.kotlin.ticktacktoe.game.data

import com.craig.kotlin.ticktacktoe.game.GameBoardDTO
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "GAMEBOARD")
internal data class GameBoardEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @OneToMany(targetEntity = Play::class, mappedBy = "id")
        val gameBoard: MutableSet<Play>,

        @Column(name = "GAME_OVER")
        val gameOver: Boolean,

        @Column(name = "WINNER")
        @Enumerated(EnumType.ORDINAL)
        val winner: Player? = null) {

    private constructor() : this(gameBoard = mutableSetOf(), gameOver = false, winner = null)

    fun toDTO() : GameBoardDTO = GameBoardDTO(id!!, gameBoard, gameOver, Optional.ofNullable(winner) )

    companion object {
        fun fromDTO(gameBoardDTO: GameBoardDTO)
                = GameBoardEntity(gameBoardDTO.id, gameBoardDTO.boardSet, gameBoardDTO.isGameOver(), gameBoardDTO.winner.orElse(null) )

    }
}

