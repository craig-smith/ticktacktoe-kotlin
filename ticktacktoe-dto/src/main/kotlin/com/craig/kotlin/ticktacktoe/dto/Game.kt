package com.craig.kotlin.ticktacktoe.game


import com.craig.kotlin.ticktacktoe.game.data.PlayDTO
import java.util.*

data class Game(var gameBoardDTOId: Long, var play: Optional<PlayDTO>, var winner: Optional<Player>?, var gameOver: Optional<Boolean>?)