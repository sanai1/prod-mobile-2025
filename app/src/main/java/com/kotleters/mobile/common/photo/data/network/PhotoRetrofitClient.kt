package com.kotleters.mobile.common.photo.data.network

import com.kotleters.mobile.common.data.network.model.BaseURL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhotoRetrofitClient {
        val photoRetrofitService: PhotoRetrofitService by lazy {
            Retrofit.Builder()
                .baseUrl(BaseURL.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PhotoRetrofitService::class.java)
        }
}