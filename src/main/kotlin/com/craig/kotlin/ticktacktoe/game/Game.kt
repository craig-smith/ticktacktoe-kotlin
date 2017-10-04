package com.craig.kotlin.ticktacktoe.game

import java.util.*

data class Game(var gameBoardDTOId: Long, var play: Optional<Play>, var winner: Optional<Player>)