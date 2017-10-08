package com.craig.kotlin.ticktacktoe.game.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity(name = "PLAY")
class Play @JsonCreator(mode=JsonCreator.Mode.DELEGATING) constructor(place: Int, player: String) {

    @Id
    @JsonIgnore
    var id: Long? = null

    @Column(name = "PLACE")
    @JsonProperty("place")
    var place: Int = place

    @Column(name = "PLAYER")
    @JsonProperty("player")
    var playerString: String = player

    @Transient
    @JsonIgnore
    var player: Player = Player.valueOf(playerString)
}