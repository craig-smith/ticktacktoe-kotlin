package com.craig.kotlin.ticktacktoe.game.data

import com.craig.kotlin.ticktacktoe.game.Player
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class PlayDTO @JsonCreator(mode=JsonCreator.Mode.DELEGATING) constructor(id: Long?, place: Int, player: String) {

    @JsonIgnore
    var id: Long? = id

    @JsonProperty("place")
    var place: Int = place

    @JsonProperty("player")
    var playerString: String = player

    @JsonIgnore
    var player: Player = Player.valueOf(playerString)


}