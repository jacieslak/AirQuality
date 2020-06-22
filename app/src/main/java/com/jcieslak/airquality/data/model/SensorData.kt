package com.jcieslak.airquality.data.model

data class SensorData(
    val key: String,
    val values: List<Value>
)

data class Value(
    val date: String,
    val value: Double
)