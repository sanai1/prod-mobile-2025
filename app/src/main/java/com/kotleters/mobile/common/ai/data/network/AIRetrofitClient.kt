package com.kotleters.mobile.common.ai.data.network

import com.kotleters.mobile.common.data.network.model.BaseURL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AIRetrofitClient {
    val aiRetrofitService: AIRetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.mistral.ai/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AIRetrofitService::class.java)
    }
}