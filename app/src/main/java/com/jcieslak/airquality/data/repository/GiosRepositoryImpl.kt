package com.jcieslak.airquality.data.repository

import com.jcieslak.airquality.data.model.Sensor
import com.jcieslak.airquality.data.model.Station
import com.jcieslak.airquality.network.GiosService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GiosRepositoryImpl @Inject constructor(
    private val giosService: GiosService
): GiosRepository {


    override fun getAllStations(): Observable<List<Station>> {
        return giosService.getAllStation()
            .toObservable()
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

    override fun getStationSensors(stationId: Int): Single<List<Sensor>> {
        return giosService.getStationsSensorList(stationId)
            .map { response ->
                when {
                    response.isSuccessful -> {
                        return@map response.body()
                    }
                    else -> {
                        throw  Exception(response.errorBody().toString())
                    }
                }
            }

    }
}