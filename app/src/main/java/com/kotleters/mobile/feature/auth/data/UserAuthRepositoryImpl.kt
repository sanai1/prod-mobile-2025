package com.kotleters.mobile.feature.auth.data

import android.content.Context
import android.util.Log
import com.kotleters.mobile.common.ResponseTemplate
import com.kotleters.mobile.common.SecretStorage
import com.kotleters.mobile.feature.auth.data.network.AuthRetrofitClient
import com.kotleters.mobile.feature.auth.data.network.model.ClientAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.CompanyAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.UserAuthLoginModel
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository

class UserAuthRepositoryImpl(
    private val context: Context
) : UserAuthRepository {
    override suspend fun register(userAuth: UserAuth): ResponseTemplate<Boolean> {
        try {
            when (userAuth) {
                is UserAuth.Client -> {
                    val token = AuthRetrofitClient.authRetrofitService.registerClient(
                        clientAuthRegisterModel = ClientAuthRegisterModel(
                            firstName = userAuth.firstName ?: "",
                            lastName = userAuth.secondName ?: "",
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute().body()?.token
                    return if (token?.isNotEmpty() == true) {
                        userAuth.apply {
                            save(email, password, token)
                        }
                        ResponseTemplate.Success(data = true)
                    } else {
                        ResponseTemplate.Error(message = "error registerClient")
                    }
                }
                is UserAuth.Company -> {
                    val token = AuthRetrofitClient.authRetrofitService.registerCompany(
                        companyAuthRegisterModel = CompanyAuthRegisterModel(
                            name = userAuth.name ?: "",
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute().body()?.token
                    return if (token?.isNotEmpty() == true) {
                        userAuth.apply {
                            save(email, password, token)
                        }
                        ResponseTemplate.Success(data = true)
                    } else {
                        ResponseTemplate.Error(message = "error registerCompany")
                    }
                }
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }

    override suspend fun auth(userAuth: UserAuth): ResponseTemplate<Boolean> {
        try {
            when (userAuth) {
                is UserAuth.Client -> {
                    val token = AuthRetrofitClient.authRetrofitService.authClient(
                        userAuthLoginModel = UserAuthLoginModel(
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute().body()?.token
                    return if (token?.isNotEmpty() == true) {
                        userAuth.apply {
                            save(email, password, token)
                        }
                        ResponseTemplate.Success(data = true)
                    } else {
                        ResponseTemplate.Error(message = "error authClient")
                    }
                }
                is UserAuth.Company -> {
                    val token = AuthRetrofitClient.authRetrofitService.authCompany(
                        userAuthLoginModel = UserAuthLoginModel(
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute().body()?.token
                    return if (token?.isNotEmpty() == true) {
                        userAuth.apply {
                            save(email, password, token)
                        }
                        ResponseTemplate.Success(data = true)
                    } else {
                        ResponseTemplate.Error(message = "error authCompany")
                    }
                }
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }

    override suspend fun checkLogIn(): Boolean = SecretStorage.readPassAndEmail(context).let {
        (it.first == null && it.second == null).not()
    }

    override suspend fun logOut() = SecretStorage.logOut(context)

    private fun save(
        email: String,
        password: String,
        token: String
    ) = SecretStorage.apply {
        savePassAndEmail(
            context = context,
            email = email,
            password = password
        )
        saveToken(
            context = context,
            token = token
        )
    }
}