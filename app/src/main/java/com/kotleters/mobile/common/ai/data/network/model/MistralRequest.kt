package com.kotleters.mobile.common.ai.data.network.model

data class MistralRequest(
    val model: String = "mistral-tiny",
    val messages: List<Message>,
    val temperature: Float = 0.7f

)

data class Message(
    val role: String,
    val content: String
)

