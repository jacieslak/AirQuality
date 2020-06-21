package com.jcieslak.airquality.data.model

data class Sensor(
    val id: Int,
    val param: Param,
    val stationId: Int
)

data class Param(
    val idParam: Int,
    val paramCode: String,
    val paramFormula: String,
    val paramName: String
)