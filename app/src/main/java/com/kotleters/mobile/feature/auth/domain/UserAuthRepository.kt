package com.kotleters.mobile.feature.auth.domain

import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.UserLogIn

interface UserAuthRepository {
    suspend fun register(userAuth: UserAuth): ResponseTemplate<Boolean>

    suspend fun auth(userAuth: UserAuth): ResponseTemplate<Boolean>

    suspend fun checkLogIn(): UserLogIn

    suspend fun logOut()
}
