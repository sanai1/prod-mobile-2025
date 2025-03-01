package com.kotleters.mobile.common.photo.data

import android.content.Context
import android.net.Uri
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.common.photo.data.network.PhotoRetrofitClient
import com.kotleters.mobile.common.photo.domain.PhotoRepository
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository

class PhotoRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : PhotoRepository {

    override suspend fun getCompanyPhoto(companyId: String): ResponseTemplate<ByteArray> {
        return try {
            val call = getPhoto(companyId)
            if (call.code() == 401) {
                updateToken()
                val callAgain = getPhoto(companyId)
               return if (callAgain.code() == 200){
                   ResponseTemplate.Success(call.body()!!)
               }else{
                   ResponseTemplate.Error(message = call.message())
               }
            }else{
                throw Exception()
            }
        } catch (e: Exception) {
            ResponseTemplate.Error(message = e.message.toString())
        }
    }

    override suspend fun addCompanyPhoto(photo: Uri): ResponseTemplate<Boolean> {
        TODO("Not yet implemented")
    }

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

    private fun getPhoto(companyId: String) =
        PhotoRetrofitClient.photoRetrofitService.getCompanyPhoto(
            token = getToken(),
            companyId = companyId
        ).execute()
}