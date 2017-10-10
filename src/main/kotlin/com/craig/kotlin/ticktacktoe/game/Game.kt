package com.craig.kotlin.ticktacktoe.game


import com.craig.kotlin.ticktacktoe.game.data.PlayDTO
import com.craig.kotlin.ticktacktoe.game.data.Player
import java.util.*

data class Game(var gameBoardDTOId: Long, var play: Optional<PlayDTO>, var winner: Optional<Player>?)