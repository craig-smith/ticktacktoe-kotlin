package com.craig.kotlin

import com.craig.kotlin.ticktacktoe.game.GameBoardDTO
import com.craig.kotlin.ticktacktoe.game.data.Play
import com.craig.kotlin.ticktacktoe.game.data.Player
import com.craig.kotlin.ticktacktoe.game.TickTackToeServiceBean
import com.craig.kotlin.ticktacktoe.game.data.PlayDTO
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class TickTackToeServiceBeanTest {

    val tickTackToe = TickTackToeServiceBean()

    @Test
    fun setPlayerMove() {
        val gameBoard = GameBoardDTO(0, mutableSetOf(), false, Optional.empty())
        val PlayDTO1 = PlayDTO(1, 1, Player.O.name)
        val PlayDTO2 = PlayDTO(2, 1, Player.X.name)
        tickTackToe.setPlayerMove(gameBoard, PlayDTO1)
        tickTackToe.setPlayerMove(gameBoard, PlayDTO2)

        assertTrue(gameBoard.boardSet.contains(PlayDTO1))
        assertTrue(gameBoard.boardSet.contains(PlayDTO2))
    }

    @Test
    fun setComputerMove() {
        val gameBoard = GameBoardDTO(0, mutableSetOf(), false, Optional.empty())
        tickTackToe.setComputerMove(gameBoard, Player.X)
        tickTackToe.setComputerMove(gameBoard, Player.X)

        assertEquals(2, gameBoard.boardSet.size)
    }

    @Test
    fun computerShouldWinIn3Moves() {
        val gameBoard = GameBoardDTO(0, mutableSetOf(), false, Optional.empty() )
        tickTackToe.setComputerMove(gameBoard, Player.X)
        tickTackToe.setComputerMove(gameBoard, Player.X)
        tickTackToe.setComputerMove(gameBoard, Player.X)
        tickTackToe.calculateWinner(gameBoard)
        assertEquals(Optional.of(Player.X), gameBoard.winner)
    }

    @Test
    fun getWinner() {
        val plays = hashSetOf(PlayDTO(1, 1, Player.X.name), PlayDTO(2, 2, Player.X.name),
                PlayDTO(3, 3, Player.X.name))
        val gameBoard = GameBoardDTO(0, plays, false, Optional.empty())
        tickTackToe.calculateWinner(gameBoard)

        assertEquals(Optional.of(Player.X), gameBoard.winner)

        val plays2 = hashSetOf(PlayDTO(4, 4, Player.O.name), PlayDTO(5, 5, Player.O.name),
                PlayDTO(6, 6,  Player.O.name), PlayDTO(1, 1, Player.X.name), PlayDTO(9, 9, Player.X.name))
        val gameBoard2 = GameBoardDTO(0, plays2, false, Optional.empty())
        tickTackToe.calculateWinner(gameBoard2)

        assertEquals(Optional.of(Player.O), gameBoard2.winner)
    }

    @Test
    fun testFullBoard() {

        val PlayDTO1 = PlayDTO(1, 1, "X")
        val PlayDTO2 = PlayDTO(2, 2, "O")
        val PlayDTO3 = PlayDTO(3, 3,  "X")
        val PlayDTO4 = PlayDTO(4, 4,"O")
        val PlayDTO5 = PlayDTO(5, 5, "X")
        val PlayDTO6 = PlayDTO(6, 6, "O")
        val PlayDTO7 = PlayDTO(7, 7, "X")
        val PlayDTO8 = PlayDTO(8, 8, "O")
        val PlayDTO9 = PlayDTO(9, 9, "X")

        var gameSet = mutableSetOf(PlayDTO1, PlayDTO2, PlayDTO3, PlayDTO4, PlayDTO5, PlayDTO6, PlayDTO7, PlayDTO8, PlayDTO9)

        var gameBoard = GameBoardDTO(0, gameSet, false, Optional.empty())

        tickTackToe.calculateWinner(gameBoard)
        tickTackToe.setComputerMove(gameBoard, Player.X)

        assertTrue(gameBoard.winner.isPresent)

    }

}