package com.jcieslak.airquality.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sensor(
    val id: Int,
    val param: Param,
    val stationId: Int
) : Parcelable

@Parcelize
data class Param(
    val idParam: Int,
    val paramCode: String,
    val paramFormula: String,
    val paramName: String
) : Parcelable

