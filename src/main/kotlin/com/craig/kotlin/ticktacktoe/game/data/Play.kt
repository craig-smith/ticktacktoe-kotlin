package com.craig.kotlin.ticktacktoe.game.data

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "PLAY")
class Play constructor(id: Long?, place: Int, player: Player) {

    @Id
    var id: Long? = null

    @Column(name = "PLACE")
    var place: Int = place

    @Column(name = "PLAYER")
    var player: Player = player

    companion object {
        fun toDTO(play: Play) : PlayDTO {
            return PlayDTO(play.id, play.place, play.player.name)
        }

        fun fromDTO(playDTO: PlayDTO): Play {
            return Play(playDTO.id, playDTO.place, Player.valueOf(playDTO.playerString))
        }
    }
}