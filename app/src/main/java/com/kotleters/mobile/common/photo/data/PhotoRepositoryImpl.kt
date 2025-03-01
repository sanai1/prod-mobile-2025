package com.kotleters.mobile.common.photo.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.common.photo.data.network.PhotoRetrofitClient
import com.kotleters.mobile.common.photo.domain.PhotoRepository
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

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
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(call.body()!!)
                } else {
                    ResponseTemplate.Error(message = call.message())
                }
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            ResponseTemplate.Error(message = e.message.toString())
        }
    }

    override suspend fun addCompanyPhoto(photo: Uri): ResponseTemplate<Boolean> {
        return try {
          val res = PhotoRetrofitClient.photoRetrofitService.addCompanyPhoto(
                token = getToken(),
                photo = compressImage(context, photo)
            ).execute()
          return if (res.code() == 200){
               ResponseTemplate.Success(true)
           }else{
               throw Exception()
           }
        } catch (e: Exception){
            ResponseTemplate.Error(message = e.message.toString())
        }
    }

    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"

    private fun compressImage(context: Context, imageUri: Uri): ByteArray{
        val quality = 40
        val contentResolver = context.contentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(imageUri)

        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, this)

            val scale = calculateInSampleSize(this)
            inSampleSize = scale
            inJustDecodeBounds = false
        }


        val bitmap: Bitmap? =
            BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri), null, options)


        val fileName = "compressed_image_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)
        val outputStream = FileOutputStream(file)

        bitmap?.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        outputStream.flush()
        outputStream.close()

        return  bitmap.let {
            val outputStream = ByteArrayOutputStream()
            it?.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.toByteArray()
        }
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,

        ): Int {
        val reqWidth = 800
        val reqHeight = 800
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

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