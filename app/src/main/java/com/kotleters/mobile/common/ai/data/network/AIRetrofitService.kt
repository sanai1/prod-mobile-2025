package com.kotleters.mobile.common.ai.data.network

import com.kotleters.mobile.common.ai.data.network.model.MistralRequest
import com.kotleters.mobile.common.ai.data.network.model.MistralResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AIRetrofitService {
    @Headers(
        "Authorization: Bearer YOUR_API_KEY",
        "Content-Type: application/json"
    )
    @POST("v1/chat/completions")
    fun getAIAdvice(
        @Body request: MistralRequest
    ): Call<MistralResponse>
}