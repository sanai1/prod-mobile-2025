package com.kotleters.mobile.feature.auth.data.network

import com.kotleters.mobile.common.data.network.model.BaseURL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthRetrofitClient {
    val authRetrofitService: AuthRetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl(BaseURL.getUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthRetrofitService::class.java)
    }
}
