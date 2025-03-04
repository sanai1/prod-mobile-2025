package com.kotleters.mobile.feature.client.data.network.service

import com.kotleters.mobile.feature.client.data.network.model.PayloadClient
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ClientGenerateQRService {
    @POST("offers/client/generateQr")
    fun getPayload(
        @Header("Authorization") token: String,
        @Query("offerId") offerId: String,
        @Query("spendBonus") spendBonus: String
    ): Call<PayloadClient>
}