package com.kotleters.mobile.common.ai.data.network

import com.kotleters.mobile.common.ai.data.network.model.MistralRequest
import com.kotleters.mobile.common.ai.data.network.model.MistralResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AIRetrofitService {
    @POST("v1/chat/completions")
    fun getAIAdvice(
        @Header("Authorization") apikey: String,
        @Body request: MistralRequest
    ): Call<MistralResponse>

}