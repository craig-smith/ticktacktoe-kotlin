package com.craig.kotlin

import com.craig.kotlin.data.BestMove
import com.craig.kotlin.data.GameBoard
import com.craig.kotlin.data.Play
import com.craig.kotlin.data.Player
import org.springframework.stereotype.Component
import java.util.*
import kotlin.reflect.KFunction2

@Component
class TickTackToeServiceBean : TickTackToeService {

    /**
     * possible board positions
     * 1 -> 2 -> 3
     * 4 -> 5 -> 6
     * 7 -> 8 -> 9
     *
     * 1 -> 5 -> 9
     * 3 -> 5 -> 7
     *
     * 1 -> 4 -> 7
     * 2 -> 5 -> 8
     * 3 -> 6 -> 9
     */
    private final val across1: List<Int> = listOf(1, 2, 3)
    private final val across2: List<Int> = listOf(4, 5, 6)
    private final val across3: List<Int> = listOf(7, 8, 9)

    private val acrossRows = listOf(across1, across2, across3)

    private final val vertical1: List<Int> = listOf(1, 4, 7)
    private final val vertical2: List<Int> = listOf(2, 5, 8)
    private final val vertical3: List<Int> = listOf(3, 6, 9)

    private final val verticalRows = listOf(vertical1, vertical2, vertical3)

    private final val diagonal1: List<Int> = listOf(1, 5, 9)
    private final val diagonal2: List<Int> = listOf(3, 5, 7)

    private final val diagonalRows = listOf(diagonal1, diagonal2)

    private val allSpots: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    private fun acrossFilter(movesList: List<Play>, rowNum: Int): List<Play> {
        return when (rowNum) {
            1 -> movesList.filter { play -> play.place in across1 }
            2 -> movesList.filter { play -> play.place in across2 }
            3 -> movesList.filter { play -> play.place in across3 }
            else -> emptyList()
        }
    }

    private fun verticalFilter(movesList: List<Play>, columnNum: Int): List<Play> {
        return when (columnNum) {
            1 -> movesList.filter { play -> play.place in vertical1 }
            2 -> movesList.filter { play -> play.place in vertical2 }
            3 -> movesList.filter { play -> play.place in vertical3 }
            else -> emptyList()
        }
    }

    private fun diagonalFilter(movesList: List<Play>, diagonalNum: Int): List<Play> {
        return when (diagonalNum) {
            1 -> movesList.filter { play -> play.place in diagonal1 }
            2 -> movesList.filter { play -> play.place in diagonal2 }
            else -> emptyList()
        }
    }

    override fun setPlayerMove(gameBoard: GameBoard, play: Play) {
        gameBoard.setMove(play)
    }

    override fun setComputerMove(gameBoard: GameBoard, player: Player) {
        val computerMove = calculateBestMove(gameBoard, player)
        gameBoard.setMove(computerMove)
    }

    private fun calculateBestMove(gameBoard: GameBoard, player: Player): Play {
        val emptySpots = findAllEmptySpots(gameBoard)
        val playerMoves = gameBoard.boardSet.filter { play -> play.player == player }

        var bestMoveList = arrayListOf<BestMove>()


        for (i in 1..3) {
             val bestMove = checkRowForWinningMove(playerMoves, emptySpots, this::acrossFilter, i, acrossRows.get(i - 1))
            if (bestMove.win) {
                return Play(bestMove.spot, player.name)
            } else {
                bestMoveList.add(bestMove)
            }
        }

        for (i in 1..3) {
             val bestMove = checkRowForWinningMove(playerMoves, emptySpots, this::verticalFilter, i, verticalRows.get(i - 1))
            if (bestMove.win) {
                return Play(bestMove.spot, player.name)
            } else {
                bestMoveList.add(bestMove)
            }
        }

        for (i in 1..2) {
            val bestMove = checkRowForWinningMove(playerMoves, emptySpots, this::diagonalFilter, i, diagonalRows.get(i - 1))
            if (bestMove.win) {
                return Play(bestMove.spot, player.name)
            } else {
                bestMoveList.add(bestMove)
            }
        }
        val matchedMove = bestMoveList.filter { it -> it.spot > 0 }
        if (matchedMove.isNotEmpty()) {
            return Play(matchedMove[0].spot, player.name)
        }

        val randomSpot = emptySpots[(Math.random() * emptySpots.size).toInt()]
        return Play(randomSpot, player.name)


    }

    private fun checkRowForWinningMove(playerMoves: List<Play>, emptySpots: List<Int>,
                                       filterFunction: KFunction2<@ParameterName(name = "movesList") List<Play>, @ParameterName(name = "rowNum") Int, List<Play>>,
                                       rowNum: Int, row: List<Int>): BestMove {
        val playerMovesOnRow = filterFunction(playerMoves, rowNum).map { play -> play.place }
        val bestMove = row.first { i -> i !in playerMovesOnRow }
        if (playerMovesOnRow.size == 2) {
            if (emptySpots.contains(bestMove)) {
                return BestMove(bestMove, true)
            }
        } else if (emptySpots.contains(bestMove)) {
            return BestMove(bestMove, false)
        }
        return BestMove(0, false)
    }

    private fun findAllEmptySpots(gameBoard: GameBoard): List<Int> {
        val playedSpots: List<Int> = gameBoard.boardSet.map { play -> play.place }
        return allSpots.filter { i: Int -> i !in playedSpots }
    }

    override fun getWinner(gameBoard: GameBoard): Optional<Player> {
        val optionalWinnerAcross = checkRow(gameBoard, this::acrossFilter)
        if (optionalWinnerAcross.isPresent) {
            return optionalWinnerAcross
        }
        val optionalWinnerVertical = checkRow(gameBoard, this::verticalFilter)
        if (optionalWinnerVertical.isPresent) {
            return optionalWinnerVertical
        }
        return checkRow(gameBoard, this::diagonalFilter)
    }

    private fun checkRow(gameBoard: GameBoard, filterFunction: KFunction2<@ParameterName(name = "movesList") List<Play>, @ParameterName(name = "rowNum") Int, List<Play>>): Optional<Player> {
        for (i in 1..3) {
            val xMatch = filterFunction(gameBoard.boardSet.toList(), i).filter { play -> play.player == Player.X }
            if (xMatch.size == 3) {
                return Optional.of(Player.X)
            }
        }

        for (i in 1..3) {
            val oMatch = filterFunction(gameBoard.boardSet.toList(), i).filter { play -> play.player == Player.O }
            if (oMatch.size == 3) {
                return Optional.of(Player.O)
            }
        }
        return Optional.empty()
    }

}