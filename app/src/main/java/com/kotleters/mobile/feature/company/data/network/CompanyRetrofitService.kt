package com.kotleters.mobile.feature.company.data.network

import com.kotleters.mobile.common.data.network.model.ClientOffers
import com.kotleters.mobile.common.domain.Payload
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel
import com.kotleters.mobile.feature.company.data.network.model.ScanQrModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CompanyRetrofitService {
    @POST("offers/create")
    fun createOffer(
        @Header("Authorization") token: String,
        @Body offer: OfferCompanyCreateModel
    ): Call<ResponseBody>

    @GET("offers/company")
    fun getOffersByCompany(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 1000,
        @Query("offset") offset: Int = 0
    ): Call<List<ClientOffers>>

    @POST("offers/company/scanQr")
    fun scanQr(
        @Header("Authorization") token: String,
        @Body payload: Payload
    ): Call<ScanQrModel>
}
