package com.kotleters.mobile.feature.company.data.network.service

import com.kotleters.mobile.feature.company.data.network.model.StatisticByDateModel
import com.kotleters.mobile.feature.company.data.network.model.StatisticByHourModel
import com.kotleters.mobile.feature.company.data.network.model.StatisticByMonthModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface StatisticRetrofitService {

    @GET("companies/stats/byDate")
    fun getStatisticByDate(
        @Header("Authorization") token: String,
    ): Call<List<StatisticByDateModel>>

    @GET("companies/stats/byHour")
    fun getStatisticByHour(
        @Header("Authorization") token: String,
    ): Call<List<StatisticByHourModel>>

    @GET("companies/stats/byMonth")
    fun getStatisticByMonth(
        @Header("Authorization") token: String,
    ): Call<List<StatisticByMonthModel>>
}