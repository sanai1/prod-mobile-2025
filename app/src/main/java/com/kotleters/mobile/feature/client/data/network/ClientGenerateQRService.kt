package com.kotleters.mobile.feature.client.data.network

import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Payload
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ClientGenerateQRService {
    @POST("offers/client/generateQr")
    fun getPayload(
        @Header("Authorization") token: String,
        @Query("offerId") offerId: String,
    ): Call<Payload>
}