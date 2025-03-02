package com.kotleters.mobile.feature.company.data.network.client

import com.kotleters.mobile.common.data.network.model.BaseURL
import com.kotleters.mobile.feature.company.data.network.service.StatisticRetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StatisticRetrofitClient {
    val statisticRetrofitService: StatisticRetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl(BaseURL.getUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StatisticRetrofitService::class.java)
    }
}