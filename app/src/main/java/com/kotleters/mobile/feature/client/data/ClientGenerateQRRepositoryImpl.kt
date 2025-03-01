package com.kotleters.mobile.feature.client.data

import android.content.Context
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.data.network.ClientGenerateQRClient
import com.kotleters.mobile.feature.client.domain.ClientGenerateQRRepository
import retrofit2.HttpException

class ClientGenerateQRRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
): ClientGenerateQRRepository {
    override suspend fun clientGenerateQRRepository(offerId: String): ResponseTemplate<String> {
        return try {
            ResponseTemplate.Success(ClientGenerateQRClient.clientGenerateQRService.getPayload(offerId, getToken()).toString())
        }catch (h: HttpException) {
            if (h.code() == 403){
                val (pass, email) = getPassAndToken()

                userAuthRepository.auth(userAuth = UserAuth.Client(
                    firstName = null,
                    secondName = null,
                    email = email!!,
                    password = pass!!
                ))
                return ResponseTemplate.Success(ClientGenerateQRClient.clientGenerateQRService.getPayload(offerId, getToken()).toString())
            }else {
                return ResponseTemplate.Error(message = "Ошибка HTTP ${h.code()}: ${h.message()}")
            }
        } catch (e: Exception) {
            ResponseTemplate.Error(message = e.message.toString())
        }
    }


    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"

    private fun getPassAndToken() = SecretStorage.readPassAndEmail(context)
}