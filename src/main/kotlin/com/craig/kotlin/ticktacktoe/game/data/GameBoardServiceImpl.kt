package com.craig.kotlin.ticktacktoe.game.data

import com.craig.kotlin.ticktacktoe.game.GameBoardDTO
import com.craig.kotlin.ticktacktoe.game.GameBoardService
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class GameBoardServiceImpl : GameBoardService {

    @Autowired
    private lateinit var gameBoardRepository: GameBoardRepository

    override fun getById(id: Long): Optional<GameBoardDTO> {
        val gameBoardEntity = gameBoardRepository.findById(id)
        return Optional.ofNullable(gameBoardEntity.orElse(null).toDTO())
    }

    override fun save(gameBoardDTO: GameBoardDTO) {
        gameBoardRepository.save(GameBoardEntity.fromDTO(gameBoardDTO))
    }

    override fun newGame(): GameBoardDTO {
        var gameEntity =  GameBoardEntity(null, mutableSetOf(), false, Optional.empty())
        gameBoardRepository.save(gameEntity)
        return gameEntity.toDTO()
    }
}