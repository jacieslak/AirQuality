package com.jcieslak.airquality.data.repository

import com.jcieslak.airquality.data.model.Station
import com.jcieslak.airquality.network.GiosService
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import java.lang.Exception
import java.net.HttpURLConnection
import javax.inject.Inject

class GiosRepositoryImpl @Inject constructor(
    private val giosService: GiosService
): GiosRepository {


    override fun getAllStations(): Single<List<Station>> {
        return giosService.getAllStation()
            .map { response ->
                when {
                    response.isSuccessful -> {
                        return@map response.body()
                    }
                    response.code() == HttpURLConnection.HTTP_BAD_REQUEST -> {
                        throw Exception()
                    }
                    else -> {
                        throw  Exception()
                    }
                }
            }
    }

}