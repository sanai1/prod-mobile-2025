package com.kotleters.mobile.common.category.data.network

import com.kotleters.mobile.common.data.network.model.BaseURL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CategoryInfoClient {
    val categoryInfoService: CategoryInfoService by lazy {
        Retrofit.Builder()
            .baseUrl(BaseURL.getUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CategoryInfoService::class.java)
    }
}
