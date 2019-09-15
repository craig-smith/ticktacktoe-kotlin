package com.craig.kotlin.ticktacktoe.game.data

import com.craig.kotlin.ticktacktoe.game.Player
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class PlayDTO constructor(id: Long?, @JsonProperty("place") place: Int, @JsonProperty("player") player: String) {


    var id: Long? = id


    var place: Int = place


    var playerString: String = player

    @JsonIgnore
    var player: Player = Player.valueOf(playerString)


}