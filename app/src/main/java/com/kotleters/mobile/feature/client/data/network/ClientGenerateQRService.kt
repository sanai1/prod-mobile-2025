package com.kotleters.mobile.feature.client.data.network

import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Payload
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ClientGenerateQRService {
    @POST("offers/client/generateQr")
    fun getPayload(
        @Body offerId: String,
        @Header("Authorization") token: String
    ): Call<Payload>
}