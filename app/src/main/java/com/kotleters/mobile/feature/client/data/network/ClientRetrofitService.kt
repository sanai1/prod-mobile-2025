package com.kotleters.mobile.feature.client.data.network

import com.kotleters.mobile.common.network.model.ClientOffers
import retrofit2.http.GET

interface ClientRetrofitService {
    @GET("/api/offers/client")
    fun getAllOffers(): ClientOffers
}