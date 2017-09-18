package com.craig.kotlin.data

import java.util.*

class GameBoard {
    val boardSet: MutableSet<Play>
    private var gameOver = false
    private var winner = Optional.empty<Player>()


    constructor(moves: MutableSet<Play>) {
        boardSet = moves
    }

    constructor() {
        boardSet = mutableSetOf()
    }

    fun setMove(play: Play){
        boardSet.add(play)
    }

    fun setGameOver(player: Player) {
        gameOver = true
        winner = Optional.of(player)
    }

}