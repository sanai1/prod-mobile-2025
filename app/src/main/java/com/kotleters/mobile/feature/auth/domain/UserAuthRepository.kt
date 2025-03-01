package com.kotleters.mobile.feature.auth.domain

import com.kotleters.mobile.common.ResponseTemplate

interface UserAuthRepository {
    suspend fun register(userAuth: UserAuth): ResponseTemplate<Boolean>

    suspend fun auth(userAuth: UserAuth): ResponseTemplate<Boolean>

    suspend fun checkLogIn(): Boolean

    suspend fun logOut()
}
