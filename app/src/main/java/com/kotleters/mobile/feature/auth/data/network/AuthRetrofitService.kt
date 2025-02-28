package com.kotleters.mobile.feature.auth.data.network

import com.kotleters.mobile.feature.auth.data.network.model.ClientAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.CompanyAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.UserAuthLoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface AuthRetrofitService {
    @GET("companies/register")
    fun registerCompany(
        @Body companyAuthRegisterModel: CompanyAuthRegisterModel
    ): Call<String>

    @GET("clients/register")
    fun registerClient(
        @Body clientAuthRegisterModel: ClientAuthRegisterModel
    ): Call<String>

    @GET("companies/login")
    fun authCompany(
        @Body userAuthLoginModel: UserAuthLoginModel
    ): Call<String>

    @GET("clients/login")
    fun authClient(
        @Body userAuthLoginModel: UserAuthLoginModel
    ): Call<String>
}
