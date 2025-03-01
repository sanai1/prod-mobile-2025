package com.kotleters.mobile.common.photo.data.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PhotoRetrofitService {

    @GET("company/{companyId}/image")
    fun getCompanyPhoto(
        @Header("Authorization") token: String,
        @Path("companyId") companyId: String
    ): Call<ByteArray>

    @POST("company/image")
    fun addCompanyPhoto(
        @Header("Authorization") token: String,
        @Body photo: ByteArray
    ): Call<String>
}