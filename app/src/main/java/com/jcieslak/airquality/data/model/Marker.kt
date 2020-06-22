package com.jcieslak.airquality.data.model

data class Marker(
    val currentPos: CurrentPosition = CurrentPosition(0f, 0f),
    val value: Double = 0.0
)

data class CurrentPosition(var x: Float, var y: Float)