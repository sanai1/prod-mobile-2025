package com.kotleters.mobile.common.ai.domain

import com.kotleters.mobile.common.ai.data.network.model.Message

interface AIRepository {
    suspend fun ChatResponce(message: Message, apiKey: String): Result<String>
}