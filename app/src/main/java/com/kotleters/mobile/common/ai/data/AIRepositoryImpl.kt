package com.kotleters.mobile.common.ai.data

import com.kotleters.mobile.common.ai.data.network.AIRetrofitClient
import com.kotleters.mobile.common.ai.data.network.model.Message
import com.kotleters.mobile.common.ai.data.network.model.MistralRequest
import com.kotleters.mobile.common.ai.domain.AIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AIRepositoryImpl: AIRepository{

   override suspend fun ChatResponce(message: Message, apiKey: String): Result<String>{
        return withContext(Dispatchers.IO){
            try {
                val res = AIRetrofitClient.aiRetrofitService.getAIAdvice(
                    request = MistralRequest(
                        messages = listOf(message)
                    ),
                    apikey = apiKey
                ).execute()

                if (res.code() == 200) {
                    val text = res.body()?.choices?.firstOrNull()?.message?.content
                    if (text != null) {
                        Result.success(text)
                    } else {
                        Result.failure(Exception("Пустой ответ от AI"))
                    }
                }else{
                    Result.failure(Exception("Ошибка ${res.code()}: ${res.errorBody()?.string()}"))

                }

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}