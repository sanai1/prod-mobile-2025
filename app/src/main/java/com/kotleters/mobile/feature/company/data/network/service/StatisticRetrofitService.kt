package com.kotleters.mobile.feature.company.data.network.service

import com.kotleters.mobile.feature.company.data.network.model.StatisticModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface StatisticRetrofitService {

    @GET("") // TODO: указать путь после `/api/`
    fun getAllStatistic(
        @Header("Authorization") token: String,
    ): Call<List<StatisticModel>>
}