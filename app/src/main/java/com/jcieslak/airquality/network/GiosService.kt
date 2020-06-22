package com.jcieslak.airquality.network

import com.jcieslak.airquality.data.model.Sensor
import com.jcieslak.airquality.data.model.SensorData
import com.jcieslak.airquality.data.model.Station
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GiosService {
    @GET("pjp-api/rest/station/findAll")
    fun getAllStation(): Observable<Response<List<Station>>>

    @GET("pjp-api/rest/station/sensors/{id}")
    fun getStationsSensorList(@Path("id") stationId: Int): Observable<Response<List<Sensor>>>

    @GET("pjp-api/rest/data/getData/{id}")
    fun getSensorData(@Path("id") sensorId: Int): Observable<Response<SensorData>>
}