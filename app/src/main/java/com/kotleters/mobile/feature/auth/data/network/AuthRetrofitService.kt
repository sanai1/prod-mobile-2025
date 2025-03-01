package com.kotleters.mobile.feature.auth.data.network

import com.kotleters.mobile.feature.auth.data.network.model.ClientAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.CompanyAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.UserAuthLoginModel
import com.kotleters.mobile.feature.auth.data.network.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitService {
    @POST("companies/register")
    fun registerCompany(
        @Body companyAuthRegisterModel: CompanyAuthRegisterModel
    ): Call<String>

    @POST("clients/register")
    fun registerClient(
        @Body clientAuthRegisterModel: ClientAuthRegisterModel
    ): Call<RegisterResponse>

    @POST("companies/login")
    fun authCompany(
        @Body userAuthLoginModel: UserAuthLoginModel
    ): Call<String>

    @POST("clients/login")
    fun authClient(
        @Body userAuthLoginModel: UserAuthLoginModel
    ): Call<String>
}
