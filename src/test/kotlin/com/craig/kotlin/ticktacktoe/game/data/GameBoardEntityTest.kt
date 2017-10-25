package com.craig.kotlin.ticktacktoe.game.data

import com.craig.kotlin.ticktacktoe.game.GameBoardDTO
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class GameBoardEntityTest {

    @Test
    fun testFromDTO() {
        val gameBoardDTO = GameBoardDTO(1, mutableSetOf(), false, Optional.empty())
        gameBoardDTO.setMove(PlayDTO(1, 1, "X"))
        gameBoardDTO.setMove(PlayDTO(2, 2, "O"))

        val gameBoardEntity = GameBoardEntity.fromDTO(gameBoardDTO)

        assertEquals(false, gameBoardEntity.gameOver)
        assertEquals(null, gameBoardEntity.winner)

        val play1 = gameBoardEntity.gameBoard.stream().filter{ e -> e.id == 1L && e.place == 1 && e.player == Player.X }.findFirst()
        assertTrue(play1.isPresent)

        val play2 = gameBoardEntity.gameBoard.stream().filter{ e -> e.id == 2L && e.place == 2 && e.player == Player.O}.findFirst()
        assertTrue(play2.isPresent)

    }
}