package com.kotleters.mobile.feature.company.data.network.client

import com.kotleters.mobile.common.data.network.model.BaseURL
import com.kotleters.mobile.feature.company.data.network.service.CompanyRetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CompanyRetrofitClient {
    val companyRetrofitService: CompanyRetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl(BaseURL.getUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CompanyRetrofitService::class.java)
    }
}
