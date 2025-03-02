package com.kotleters.mobile.common.ai.data.network

import com.kotleters.mobile.common.ai.data.network.model.MistralRequest
import com.kotleters.mobile.common.ai.data.network.model.MistralResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AIRetrofitService {
    @Headers(
        "Authorization: Bearer S7nRSapsiO5d7YUT5OpsIw1Uh0sdYfAs",
        "Content-Type: application/json"
    )
    @POST("v1/chat/completions")
    fun getAIAdvice(
        @Body request: MistralRequest
    ): Call<MistralResponse>
}