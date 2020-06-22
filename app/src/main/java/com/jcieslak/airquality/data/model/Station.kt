package com.jcieslak.airquality.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Station(
    val addressStreet: String?,
    val city: City,
    val gegrLat: String,
    val gegrLon: String,
    val id: Int,
    val stationName: String,
    var sensors: List<Sensor>
) : Parcelable

@Parcelize
data class Commune(
    val communeName: String,
    val districtName: String,
    val provinceName: String
) : Parcelable

@Parcelize
data class City(
    val commune: Commune,
    val id: Int,
    val name: String
) : Parcelable