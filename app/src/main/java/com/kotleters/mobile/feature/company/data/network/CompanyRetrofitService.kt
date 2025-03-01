package com.kotleters.mobile.feature.company.data.network

import com.kotleters.mobile.common.data.network.model.ClientOffers
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel
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
    ): Call<String>

    @GET("offers/company")
    fun getOffersByCompany(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<List<ClientOffers>>
}
