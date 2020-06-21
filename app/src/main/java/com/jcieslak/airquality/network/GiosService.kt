package com.jcieslak.airquality.network

import com.jcieslak.airquality.data.model.Sensor
import com.jcieslak.airquality.data.model.Station
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GiosService {
    @GET("pjp-api/rest/station/findAll")
    fun getAllStation(): Single<Response<List<Station>>>

    @GET("pjp-api/rest/station/sensors/{id}")
    fun getStationsSensorList(@Path("id") stationId: Int): Single<Response<List<Sensor>>>
}