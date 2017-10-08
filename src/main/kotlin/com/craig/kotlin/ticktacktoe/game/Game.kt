package com.craig.kotlin.ticktacktoe.game

import com.craig.kotlin.ticktacktoe.game.data.Play
import com.craig.kotlin.ticktacktoe.game.data.Player
import java.util.*

data class Game(var gameBoardDTOId: Long, var play: Optional<Play>, var winner: Optional<Player>)