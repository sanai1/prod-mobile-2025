package com.kotleters.mobile.feature.auth.data

import android.content.Context
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.SecretStorage
import com.kotleters.mobile.common.domain.UserLogIn
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
                    val call = AuthRetrofitClient.authRetrofitService.registerClient(
                        clientAuthRegisterModel = ClientAuthRegisterModel(
                            firstName = userAuth.firstName!!,
                            lastName = userAuth.secondName!!,
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute()
                    return if (call.code() == 200) {
                        userAuth.apply {
                            save(email, password, UserLogIn.CLIENT, call.body()!!.token)
                        }
                        ResponseTemplate.Success(data = true)
                    } else {
                        ResponseTemplate.Error(message = "error registerClient")
                    }
                }
                is UserAuth.Company -> {
                    val call = AuthRetrofitClient.authRetrofitService.registerCompany(
                        companyAuthRegisterModel = CompanyAuthRegisterModel(
                            email = userAuth.email,
                            password = userAuth.password,
                            name = userAuth.name ?: "",
                            category_id = userAuth.categoryId!!
                        )
                    ).execute()
                    return if (call.code() == 200) {
                        userAuth.apply {
                            save(email, password, UserLogIn.COMPANY, call.body()!!.token)
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
                    val call = AuthRetrofitClient.authRetrofitService.authClient(
                        userAuthLoginModel = UserAuthLoginModel(
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute()
                    return if (call.code() == 200) {
                        userAuth.apply {
                            save(email, password, UserLogIn.CLIENT, call.body()!!.token)
                        }
                        ResponseTemplate.Success(data = true)
                    } else {
                        ResponseTemplate.Error(message = "error authClient")
                    }
                }
                is UserAuth.Company -> {
                    val call = AuthRetrofitClient.authRetrofitService.authCompany(
                        userAuthLoginModel = UserAuthLoginModel(
                            email = userAuth.email,
                            password = userAuth.password
                        )
                    ).execute()
                    return if (call.code() == 200) {
                        userAuth.apply {
                            save(email, password, UserLogIn.COMPANY, call.body()!!.token)
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

    override suspend fun checkLogIn() = SecretStorage.readPassAndEmail(context).let {
        if (it.first == null && it.second == null && it.third == null) {
            UserLogIn.NOT_FOUND
        } else {
            it.third!!
        }
    }

    override suspend fun logOut() = SecretStorage.logOut(context)

    private fun save(
        email: String,
        password: String,
        userLogIn: UserLogIn,
        token: String
    ) = SecretStorage.apply {
        savePassAndEmail(
            context = context,
            email = email,
            password = password,
            companyOrUser = userLogIn,
        )
        saveToken(
            context = context,
            token = token
        )
    }
}