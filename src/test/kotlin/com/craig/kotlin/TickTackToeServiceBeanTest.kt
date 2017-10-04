package com.craig.kotlin

import com.craig.kotlin.ticktacktoe.game.GameBoardDTO
import com.craig.kotlin.ticktacktoe.game.Play
import com.craig.kotlin.ticktacktoe.game.Player
import com.craig.kotlin.ticktacktoe.game.TickTackToeServiceBean
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class TickTackToeServiceBeanTest {

    val tickTackToe = TickTackToeServiceBean()

    @Test
    fun setPlayerMove() {
        val gameBoard = GameBoardDTO(0, mutableSetOf(), false, Optional.empty())
        val play1 = Play(1, Player.O.name)
        val play2 = Play(2, Player.X.name)
        tickTackToe.setPlayerMove(gameBoard, play1)
        tickTackToe.setPlayerMove(gameBoard, play2)

        assertTrue(gameBoard.boardSet.contains(play1))
        assertTrue(gameBoard.boardSet.contains(play2))
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
        val plays = hashSetOf(Play(1, Player.X.name), Play(2, Player.X.name), Play(3, Player.X.name))
        val gameBoard = GameBoardDTO(0, plays, false, Optional.empty())
        tickTackToe.calculateWinner(gameBoard)

        assertEquals(Optional.of(Player.X), gameBoard.winner)

        val plays2 = hashSetOf(Play(4, Player.O.name), Play(5, Player.O.name), Play(6, Player.O.name), Play(1, Player.X.name), Play(9, Player.X.name))
        val gameBoard2 = GameBoardDTO(0, plays2, false, Optional.empty())
        tickTackToe.calculateWinner(gameBoard2)

        assertEquals(Optional.of(Player.O), gameBoard2.winner)
    }

    @Test
    fun testFullBoard() {

        val play1 = Play(1, "X")
        val play2 = Play(2, "O")
        val play3 = Play(3, "X")
        val play4 = Play(4, "O")
        val play5 = Play(5, "X")
        val play6 = Play(6, "O")
        val play7 = Play(7, "X")
        val play8 = Play(8, "O")
        val play9 = Play(9, "X")

        var gameSet = mutableSetOf(play1, play2, play3, play4, play5, play6, play7, play8, play9)

        var gameBoard = GameBoardDTO(0, gameSet, false, Optional.empty())

        tickTackToe.calculateWinner(gameBoard)
        tickTackToe.setComputerMove(gameBoard, Player.X)

        assertTrue(gameBoard.winner.isPresent)

    }

}