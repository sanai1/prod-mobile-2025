package com.kotleters.mobile.feature.auth.domain

import com.kotleters.mobile.common.ResponseTemplate

interface UserAuthRepository {
    suspend fun register(userAuth: UserAuth): ResponseTemplate<Boolean>

    suspend fun auth(emailPassword: Pair<String, String>): ResponseTemplate<Boolean>
}
