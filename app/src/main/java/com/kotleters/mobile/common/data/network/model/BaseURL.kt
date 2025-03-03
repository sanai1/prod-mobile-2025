package com.kotleters.mobile.common.data.network.model

object BaseURL {
    private var BASE_URL: String? = null

    init {
        BASE_URL = "https://prod-team-10-avk8n3cp.final.prodcontest.ru/api/"
    }

    fun getUrl() = BASE_URL!!
}
