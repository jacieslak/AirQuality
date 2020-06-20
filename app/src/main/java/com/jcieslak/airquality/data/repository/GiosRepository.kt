package com.jcieslak.airquality.data.repository

import com.jcieslak.airquality.data.model.Station
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

/**
 * Repository which is responsible for all operations connected with get data from gios api
 */
interface GiosRepository {

    fun getAllStations(): Single<List<Station>>

}