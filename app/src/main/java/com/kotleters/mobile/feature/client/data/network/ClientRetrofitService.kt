package com.kotleters.mobile.feature.client.data.network

import com.kotleters.mobile.common.data.network.model.ClientOffers
import retrofit2.http.GET
import retrofit2.http.Header

interface ClientRetrofitService {
    @GET("offers/client")
    fun getAllOffers(
        @Header ("Authorization") token: String
    ): List<ClientOffers>
}