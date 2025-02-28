package com.kotleters.mobile.feature.auth.data

import android.content.Context
import com.kotleters.mobile.common.ResponseTemplate
import com.kotleters.mobile.common.SecretStorage
import com.kotleters.mobile.feature.auth.data.network.AuthRetrofitClient
import com.kotleters.mobile.feature.auth.data.network.model.ClientAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.CompanyAuthRegisterModel
import com.kotleters.mobile.feature.auth.data.network.model.UserAuthLoginModel
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val context: Context
) : UserAuthRepository {
    override suspend fun register(userAuth: UserAuth): ResponseTemplate<Boolean> {
        try {
            when (userAuth) {
                is UserAuth.Client -> {
                    AuthRetrofitClient.authRetrofitService.registerClient(
                        clientAuthRegisterModel = ClientAuthRegisterModel(
                            firstName = userAuth.firstName ?: "",
                            lastName = userAuth.secondName ?: "",
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute().body().also {
                        return if (it?.isNotEmpty() == true) {
                            SecretStorage.savePassAndEmail(
                                context = context,
                                email = userAuth.email,
                                password = userAuth.password
                            )
                            SecretStorage.saveToken(
                                context = context,
                                token = it
                            )
                            ResponseTemplate.Success(
                                data = true
                            )
                        } else {
                            ResponseTemplate.Error(
                                message = "error registerClient"
                            )
                        }
                    }
                }
                is UserAuth.Company -> {
                    AuthRetrofitClient.authRetrofitService.registerCompany(
                        companyAuthRegisterModel = CompanyAuthRegisterModel(
                            name = userAuth.name ?: "",
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute().body().also {
                        return if (it?.isNotEmpty() == true) {
                            SecretStorage.savePassAndEmail(
                                context = context,
                                email = userAuth.email,
                                password = userAuth.password
                            )
                            SecretStorage.saveToken(
                                context = context,
                                token = it
                            )
                            ResponseTemplate.Success(
                                data = true
                            )
                        } else {
                            ResponseTemplate.Error(
                                message = "error registerCompany"
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(
                message = e.message.toString()
            )
        }
    }

    override suspend fun auth(userAuth: UserAuth): ResponseTemplate<Boolean> {
        try {
            when (userAuth) {
                is UserAuth.Client -> {
                    AuthRetrofitClient.authRetrofitService.authClient(
                        userAuthLoginModel = UserAuthLoginModel(
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute().body().also { 
                        return if (it?.isNotEmpty() == true) {
                            SecretStorage.saveToken(
                                context = context,
                                token = it
                            )
                            ResponseTemplate.Success(
                                data = true
                            )
                        } else {
                            ResponseTemplate.Error(
                                message = "error authClient"
                            )
                        }
                    }
                }
                is UserAuth.Company -> {
                    AuthRetrofitClient.authRetrofitService.authCompany(
                        userAuthLoginModel = UserAuthLoginModel(
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute().body().also { 
                        return if (it?.isNotEmpty() == true) {
                            SecretStorage.saveToken(
                                context = context,
                                token = it
                            )
                            ResponseTemplate.Success(
                                data = true
                            )
                        } else {
                            ResponseTemplate.Error(
                                message = "error authCompany"
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(
                message = e.message.toString()
            )
        }
    }
}