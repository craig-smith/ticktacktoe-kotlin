package com.craig.kotlin.ticktacktoe.game.data

import javax.persistence.*

@Entity(name = "PLAY")
class Play constructor(id: Long?, gameBoardId: Long?, place: Int, player: Player) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id

    @Column(name = "GAMEBOARD_ID")
    var gameBoardId: Long? = gameBoardId

    @Column(name = "PLACE")
    var place: Int = place

    @Column(name = "PLAYER")
    var player: Player = player

    companion object {
        fun toDTO(play: Play) : PlayDTO {
            return PlayDTO(play.id, play.place, play.player.name)
        }

        fun fromDTO(playDTO: PlayDTO, gameBoardId: Long?): Play {
            val play = Play(playDTO.id, gameBoardId,  playDTO.place, Player.valueOf(playDTO.playerString))
            return play
        }
    }
}