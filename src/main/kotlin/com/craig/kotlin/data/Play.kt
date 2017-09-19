package com.craig.kotlin.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty


class Play @JsonCreator(mode=JsonCreator.Mode.DELEGATING) constructor(place: Int, player: String) {

    @JsonProperty("place")
    var place: Int = place

    @JsonProperty("player")
    var playerString: String = player

    @JsonIgnore
    var player: Player = Player.valueOf(playerString)
}