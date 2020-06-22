package com.jcieslak.airquality.data.repository

import com.jcieslak.airquality.data.model.Sensor
import com.jcieslak.airquality.data.model.SensorData
import com.jcieslak.airquality.data.model.Station
import io.reactivex.rxjava3.core.Observable

/**
 * Repository which is responsible for all operations connected with get data from Gios api
 */
interface GiosRepository {

    /**
     *  Get all station data from GIOS api
     */
    fun getAllStations(): Observable<List<Station>>

    /**
     * Get all station sensors
     */
    fun getStationSensors(stationId: Int): Observable<List<Sensor>>

    /**
     * Get all station sensors data
     */
    fun getSensorData(sensorId: Int): Observable<SensorData>
}