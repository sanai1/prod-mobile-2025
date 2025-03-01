package com.kotleters.mobile.feature.client.data.network.client

import com.kotleters.mobile.common.data.network.model.BaseURL
import com.kotleters.mobile.feature.client.data.network.service.ClientRetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientRetrofitClient {
    val clientRetrofitService: ClientRetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl(BaseURL.getUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClientRetrofitService::class.java)
    }
}