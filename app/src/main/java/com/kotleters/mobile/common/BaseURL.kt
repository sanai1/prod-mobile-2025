package com.kotleters.mobile.common

object BaseURL {
    private var BASE_URL: String? = null

    init {
        BASE_URL = "http://prod-team-10-avk8n3cp.final.prodcontest.ru/api/"
    }

    fun getUrl() = BASE_URL!!
}
