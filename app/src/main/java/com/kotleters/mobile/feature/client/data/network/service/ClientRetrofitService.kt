package com.kotleters.mobile.feature.client.data.network.service

import com.kotleters.mobile.common.data.network.model.ClientOfferModel
import com.kotleters.mobile.feature.client.data.network.model.TargetInfoModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

interface ClientRetrofitService {
    @GET("offers/client")
    fun getAllOffers(
        @Header ("Authorization") token: String,
        @Query("limit") limit: Int = 1000,
        @Query("offset") offset: Int = 0
    ): Call<List<ClientOfferModel>>

    @PUT("clients/profile")
    fun updateTarget(
        @Header("Authorization") token: String,
        @Body targetInfoModel: TargetInfoModel
    ): Call<String>
}