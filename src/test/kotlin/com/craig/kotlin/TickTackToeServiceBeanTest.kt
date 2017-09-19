package com.craig.kotlin

import com.craig.kotlin.data.GameBoard
import com.craig.kotlin.data.Play
import com.craig.kotlin.data.Player
import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.collections.HashSet

class TickTackToeServiceBeanTest {

    val tickTackToe = TickTackToeServiceBean()

    @Test
    fun setPlayerMove() {
        val gameBoard = GameBoard()
        val play1 = Play(1, Player.O.name)
        val play2 = Play(2, Player.X.name)
        tickTackToe.setPlayerMove(gameBoard, play1)
        tickTackToe.setPlayerMove(gameBoard, play2)

        assertTrue(gameBoard.boardSet.contains(play1))
        assertTrue(gameBoard.boardSet.contains(play2))
    }

    @Test
    fun setComputerMove() {
        val gameBoard = GameBoard()
        tickTackToe.setComputerMove(gameBoard, Player.X)
        tickTackToe.setComputerMove(gameBoard, Player.X)

        assertEquals(2, gameBoard.boardSet.size)
    }

    @Test
    fun computerShouldWinIn3Moves() {
        val gameBoard = GameBoard()
        tickTackToe.setComputerMove(gameBoard, Player.X)
        tickTackToe.setComputerMove(gameBoard, Player.X)
        tickTackToe.setComputerMove(gameBoard, Player.X)

        assertEquals(Optional.of(Player.X), tickTackToe.getWinner(gameBoard))
    }

    @Test
    fun getWinner() {
        val plays = hashSetOf(Play(1, Player.X.name), Play(2, Player.X.name), Play(3, Player.X.name))
        val gameBoard = GameBoard(plays)
        val result = tickTackToe.getWinner(gameBoard)

        assertEquals(Optional.of(Player.X), result)

        val plays2 = hashSetOf(Play(4, Player.O.name), Play(5, Player.O.name), Play(6, Player.O.name), Play(1, Player.X.name), Play(9, Player.X.name))
        val gameBoard2 = GameBoard(plays2)
        val result2 = tickTackToe.getWinner(gameBoard2)

        assertEquals(Optional.of(Player.O), result2)
    }

}