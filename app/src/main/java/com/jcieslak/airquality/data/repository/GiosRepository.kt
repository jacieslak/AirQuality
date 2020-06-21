package com.jcieslak.airquality.data.repository

import com.jcieslak.airquality.data.model.Sensor
import com.jcieslak.airquality.data.model.Station
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

/**
 * Repository which is responsible for all operations connected with get data from Gios api
 */
interface GiosRepository {

    fun getAllStations(): Observable<List<Station>>

    fun getStationSensors(stationId: Int): Single<List<Sensor>>

}