package com.craig.kotlin.data

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class Play(place: Int, player: String) {

    var place: Int = place
    @JsonProperty("player")
    var playerString: String = player

    @JsonIgnore
    var player: Player = Player.valueOf(playerString)
}