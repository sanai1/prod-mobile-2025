package com.kotleters.mobile.feature.auth.data.network

import com.kotleters.mobile.feature.auth.data.network.model.ClientAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.CompanyAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.UserAuthLoginModel
import com.kotleters.mobile.feature.auth.data.network.model.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitService {
    @POST("companies/register")
    fun registerCompany(
        @Body companyAuthRegisterModel: CompanyAuthRegisterModel
    ): Call<AuthResponse>

    @POST("clients/register")
    fun registerClient(
        @Body clientAuthRegisterModel: ClientAuthRegisterModel
    ): Call<AuthResponse>

    @POST("companies/login")
    fun authCompany(
        @Body userAuthLoginModel: UserAuthLoginModel
    ): Call<AuthResponse>

    @POST("clients/login")
    fun authClient(
        @Body userAuthLoginModel: UserAuthLoginModel
    ): Call<AuthResponse>
}

