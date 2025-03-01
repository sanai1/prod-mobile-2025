package com.kotleters.mobile.feature.client.data

import android.content.Context
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.CompanyMapper
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.data.network.client.ClientRetrofitClient
import com.kotleters.mobile.feature.client.data.network.model.TargetInfoModel
import com.kotleters.mobile.feature.client.domain.ClientRepository
import com.kotleters.mobile.feature.client.domain.TargetInfo

class ClientRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : ClientRepository {

    override suspend fun getAllOffers(): ResponseTemplate<List<Company>> {
        try {
            val call = getOffers()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = CompanyMapper.map(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getOffers()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = CompanyMapper.map(callAgain.body()!!)
                    )
                } else {
                    ResponseTemplate.Error(message = callAgain.message())
                }
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }

    override suspend fun updateTarget(targetInfo: TargetInfo): ResponseTemplate<Boolean> {
        try {
            val call = updateTargetRetrofit(targetInfo)
            if (call.code() == 200) {
                return ResponseTemplate.Success(data = true)
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = updateTargetRetrofit(targetInfo)
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(data = true)
                } else {
                    ResponseTemplate.Error(message = callAgain.message())
                }
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }

    private fun getOffers() = ClientRetrofitClient.clientRetrofitService.getAllOffers(
        token = getToken()
    ).execute()

    private fun updateTargetRetrofit(targetInfo: TargetInfo) =
        ClientRetrofitClient.clientRetrofitService.updateTarget(
            token = getToken(),
            targetInfoModel = TargetInfoModel(
                age = targetInfo.age,
                gender = targetInfo.gender.name
            )
        ).execute()

    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"

    private suspend fun updateToken() {
        val triple = SecretStorage.readPassAndEmail(context)
        userAuthRepository.auth(
            userAuth = UserAuth.Company(
                name = null,
                email = triple.first!!,
                password = triple.second!!
            )
        )
    }
}