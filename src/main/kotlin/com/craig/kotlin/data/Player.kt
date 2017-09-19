package com.craig.kotlin.data

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonValue

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class Player{
    X,O;

    @JsonValue
    fun getValue() :String {
        return this.name
    }
}