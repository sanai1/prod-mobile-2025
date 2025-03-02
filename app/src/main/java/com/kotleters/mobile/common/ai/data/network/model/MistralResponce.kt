package com.kotleters.mobile.common.ai.data.network.model

data class MistralResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)
