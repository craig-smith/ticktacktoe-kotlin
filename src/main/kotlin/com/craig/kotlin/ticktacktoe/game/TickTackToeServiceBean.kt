package com.craig.kotlin.ticktacktoe.game

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

    override fun setPlayerMove(gameBoardDTO: GameBoardDTO, play: Play) {
        gameBoardDTO.setMove(play)
    }

    override fun setComputerMove(gameBoardDTO: GameBoardDTO, player: Player): Play {
        val bestComputerMove = calculateBestMove(gameBoardDTO, player)
        val bestOpponentMove =  if (player == Player.X) {
            calculateBestMove(gameBoardDTO, Player.O)
        } else {
            calculateBestMove(gameBoardDTO, Player.X)
        }

        return when {
            bestComputerMove.win -> {
                gameBoardDTO.setMove(Play(bestComputerMove.spot, player.name))
                Play(bestComputerMove.spot, player.name)
            }
            else -> if(bestOpponentMove.win) {
                gameBoardDTO.setMove(Play(bestOpponentMove.spot, player.name))
                Play(bestOpponentMove.spot, player.name)
            } else {
                gameBoardDTO.setMove(Play(bestComputerMove.spot, player.name))
                Play(bestComputerMove.spot, player.name)
            }
        }

    }

    private fun calculateBestMove(gameBoardDTO: GameBoardDTO, player: Player): BestMove {
        val emptySpots = findAllEmptySpots(gameBoardDTO)
        val playerMoves = gameBoardDTO.boardSet.filter { play -> play.player == player }

        var bestMoveList = arrayListOf<BestMove>()


        for (i in 1..3) {
            val bestMove = checkRowForWinningMove(playerMoves, emptySpots, this::acrossFilter, i, acrossRows.get(i - 1))
            if (bestMove.win) {
                return bestMove
            } else {
                bestMoveList.add(bestMove)
            }
        }

        for (i in 1..3) {
            val bestMove = checkRowForWinningMove(playerMoves, emptySpots, this::verticalFilter, i, verticalRows.get(i - 1))
            if (bestMove.win) {
                return bestMove
            } else {
                bestMoveList.add(bestMove)
            }
        }

        for (i in 1..2) {
            val bestMove = checkRowForWinningMove(playerMoves, emptySpots, this::diagonalFilter, i, diagonalRows.get(i - 1))
            if (bestMove.win) {
                return bestMove
            } else {
                bestMoveList.add(bestMove)
            }
        }
        return findBestMoveIfNoWin(bestMoveList, emptySpots, player)
    }

    private fun findBestMoveIfNoWin(bestMoveList: List<BestMove>, emptySpots: List<Int>, player: Player) : BestMove {
        val matchedMove = bestMoveList.filter { it -> it.spot > 0 }
        if (matchedMove.isNotEmpty()) {
            return BestMove(matchedMove[0].spot, false);
        }

        return if(emptySpots.isNotEmpty()) {
            val randomSpot = emptySpots[(Math.random() * emptySpots.size).toInt()]
            BestMove(randomSpot, false)
        } else BestMove(0, false)
    }

    private fun checkRowForWinningMove(playerMoves: List<Play>, emptySpots: List<Int>,
                                       filterFunction: KFunction2<@ParameterName(name = "movesList") List<Play>, @ParameterName(name = "rowNum") Int, List<Play>>,
                                       rowNum: Int, row: List<Int>): BestMove {
        val playerMovesOnRow = filterFunction(playerMoves, rowNum).map { play -> play.place }
        if (playerMovesOnRow.size != 3) {
            val bestMove = row.first { i -> i !in playerMovesOnRow }
            if (playerMovesOnRow.size == 2) {
                if (emptySpots.contains(bestMove)) {
                    return BestMove(bestMove, true)
                }
            } else if (emptySpots.contains(bestMove)) {
                return BestMove(bestMove, false)
            }
        }
        return BestMove(0, false)

    }

    private fun findAllEmptySpots(gameBoardDTO: GameBoardDTO): List<Int> {
        val playedSpots: List<Int> = gameBoardDTO.boardSet.map { play -> play.place }
        return allSpots.filter { i: Int -> i !in playedSpots }
    }

    override fun calculateWinner(gameBoardDTO: GameBoardDTO) {
        val optionalWinnerAcross = checkRow(gameBoardDTO, this::acrossFilter)
        if (optionalWinnerAcross.isPresent) {
            gameBoardDTO.setGameOver(optionalWinnerAcross)
        }
        val optionalWinnerVertical = checkRow(gameBoardDTO, this::verticalFilter)
        if (optionalWinnerVertical.isPresent) {
            gameBoardDTO.setGameOver(optionalWinnerVertical)
        }
        val optionalWinnerDiagonal = checkRow(gameBoardDTO, this::diagonalFilter)
        if (optionalWinnerDiagonal.isPresent) {
            gameBoardDTO.setGameOver(optionalWinnerDiagonal)
        }

        val emptySpots = findAllEmptySpots(gameBoardDTO)
        if( emptySpots.isEmpty() ) {
            gameBoardDTO.setGameOver(Optional.empty())
        }
    }

    private fun checkRow(gameBoardDTO: GameBoardDTO, filterFunction: KFunction2<@ParameterName(name = "movesList") List<Play>, @ParameterName(name = "rowNum") Int, List<Play>>): Optional<Player> {
        for (i in 1..3) {
            val xMatch = filterFunction(gameBoardDTO.boardSet.toList(), i).filter { play -> play.player == Player.X }
            if (xMatch.size == 3) {
                return Optional.of(Player.X)
            }
        }

        for (i in 1..3) {
            val oMatch = filterFunction(gameBoardDTO.boardSet.toList(), i).filter { play -> play.player == Player.O }
            if (oMatch.size == 3) {
                return Optional.of(Player.O)
            }
        }
        return Optional.empty()
    }

}