package com.craig.kotlin.ticktacktoe.game.data

import com.craig.kotlin.ticktacktoe.game.GameBoardDTO
import com.craig.kotlin.ticktacktoe.game.data.Play.Companion.toDTO
import java.util.*
import java.util.stream.Collectors
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

    fun toDTO(): GameBoardDTO {
        val gameBoardSet = gameBoard.stream().map { t: Play? -> toDTO(t!!) }.collect(Collectors.toSet())
        return GameBoardDTO(id!!, gameBoardSet, gameOver, Optional.ofNullable(winner))
    }

    companion object {
        fun fromDTO(gameBoardDTO: GameBoardDTO): GameBoardEntity {
           return  GameBoardEntity(gameBoardDTO.id,
                    gameBoardDTO.boardSet.stream().map { e -> Play.fromDTO(e) }.collect(Collectors.toSet()),
                    gameBoardDTO.isGameOver(), gameBoardDTO.winner.orElse(null))
        }


    }
}

