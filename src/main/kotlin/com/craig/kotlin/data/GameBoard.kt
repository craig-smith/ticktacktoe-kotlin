package com.craig.kotlin.data

import java.util.*

class GameBoard {
    val boardSet: MutableSet<Play>
    var gameOver = false
    var winner = Optional.empty<Player>()


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