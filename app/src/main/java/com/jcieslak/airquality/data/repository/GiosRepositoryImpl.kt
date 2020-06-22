package com.jcieslak.airquality.data.repository

import com.jcieslak.airquality.data.model.Sensor
import com.jcieslak.airquality.data.model.SensorData
import com.jcieslak.airquality.data.model.Station
import com.jcieslak.airquality.network.GiosService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GiosRepositoryImpl @Inject constructor(
    private val giosService: GiosService
) : GiosRepository {


    override fun getAllStations(): Observable<List<Station>> {
        return giosService.getAllStation()
            .map { response ->
                when {
                    response.isSuccessful -> {
                        return@map response.body()
                    }
                    else -> { //TODO better error handling
                        throw  Exception(response.errorBody().toString())
                    }
                }
            }
    }

    override fun getStationSensors(stationId: Int): Observable<List<Sensor>> {
        return giosService.getStationsSensorList(stationId)
            .map { response ->
                when {
                    response.isSuccessful -> {
                        return@map response.body()
                    }
                    else -> { //TODO better error handling
                        throw  Exception(response.errorBody().toString())
                    }
                }
            }
    }

    override fun getSensorData(sensorId: Int): Observable<SensorData> {
        return giosService.getSensorData(sensorId)
            .map { response ->
                when {
                    response.isSuccessful -> {
                        return@map response.body()
                    }
                    else -> { //TODO better error handling
                        throw Exception(response.errorBody().toString())
                    }
                }
            }
    }
}