package com.craig.kotlin.ticktacktoe.game.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface GameBoardRepository : JpaRepository<GameBoardEntity, Long> {
}