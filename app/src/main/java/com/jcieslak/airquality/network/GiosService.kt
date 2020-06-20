package com.jcieslak.airquality.network

import com.jcieslak.airquality.data.model.Station
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface GiosService {
    @GET("/station/findAll")
    fun getAllStation(): Single<Response<List<Station>>>
}