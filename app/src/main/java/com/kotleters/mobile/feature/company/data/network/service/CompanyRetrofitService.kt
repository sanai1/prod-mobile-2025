package com.kotleters.mobile.feature.company.data.network.service

import com.kotleters.mobile.common.data.network.model.ClientOfferModel
import com.kotleters.mobile.common.domain.LacunaModel
import com.kotleters.mobile.feature.company.data.network.model.CompanyProfileModel
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel
import com.kotleters.mobile.feature.company.data.network.model.PayloadCompany
import com.kotleters.mobile.feature.company.data.network.model.ScanQrModel
import com.kotleters.mobile.feature.company.data.network.model.StatisticByMonthModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CompanyRetrofitService {
    @GET("api/companies/profile")
    fun getProfile(
        @Header("Authorization") token: String,
    ): Call<CompanyProfileModel>

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
    ): Call<List<ClientOfferModel>>

    @POST("offers/company/scanQr")
    fun scanQr(
        @Header("Authorization") token: String,
        @Body payload: PayloadCompany
    ): Call<ScanQrModel>

    @GET("companies/stats/byMonth")
    fun getStatisticByMonth(
        @Header("Authorization") token: String,
    ): Call<List<StatisticByMonthModel>>

    @GET("gap")
    fun getLacuna(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 1000
    ): Call<List<LacunaModel>>
}
