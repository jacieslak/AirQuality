package com.jcieslak.airquality.data.model

data class Station(
    val addressStreet: String,
    val city: City,
    val gegrLat: String,
    val gegrLon: String,
    val id: Int,
    val stationName: String,
    var sensors: List<Sensor>
)

data class Commune(
    val communeName: String,
    val districtName: String,
    val provinceName: String
)

data class City(
    val commune: Commune,
    val id: Int,
    val name: String
)