package com.kotleters.mobile.common.photo.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.SecretStorage
import com.kotleters.mobile.common.photo.data.network.PhotoRetrofitClient
import com.kotleters.mobile.common.photo.domain.PhotoRepository
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import java.io.ByteArrayOutputStream
import java.io.File

class PhotoRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : PhotoRepository {

    override suspend fun getCompanyPhoto(companyId: String): ResponseTemplate<ByteArray> {
        return try {
            val call = getPhoto(companyId)
            Log.d("CODE", call.code().toString())
            if (call.code() == 401) {
                updateToken()
                val callAgain = getPhoto(companyId)
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(data = call.body()!!)
                } else {
                    ResponseTemplate.Error(message = call.message())
                }
            } else if (call.code() == 200) {
                ResponseTemplate.Success(data = call.body()!!)
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            ResponseTemplate.Error(message = e.message.toString())
        }
    }

    override suspend fun addCompanyPhoto(photo: Uri): ResponseTemplate<Boolean> {
        return try {
            val call = addPhoto(photo)
            if (call.code() == 401) {
                updateToken()
                val callAgain = addPhoto(photo)
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(true)
                } else {
                    ResponseTemplate.Error(message = call.message())
                }
            } else if (call.code() == 200) {
                ResponseTemplate.Success(true)
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            ResponseTemplate.Error(message = e.message.toString())
        }
    }

    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"

    private fun compressImage(imageUri: Uri, quality: Int = 100): ByteArray {
        val file = imageUri.toFile()
        return file.readBytes()
    }

    private suspend fun updateToken() {
        val triple = SecretStorage.readPassAndEmail(context)
        userAuthRepository.auth(
            userAuth = UserAuth.Company(
                email = triple.first!!,
                password = triple.second!!
            )
        )
    }

    private fun addPhoto(photo: Uri) = PhotoRetrofitClient.photoRetrofitService.addCompanyPhoto(
        token = getToken(),
        photo = compressImage(imageUri = photo)
    ).execute()

    private fun getPhoto(companyId: String) =
        PhotoRetrofitClient.photoRetrofitService.getCompanyPhoto(
            token = getToken(),
            companyId = companyId
        ).execute()
}