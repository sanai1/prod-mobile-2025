package com.kotleters.mobile.common.photo.domain

import android.net.Uri
import com.kotleters.mobile.common.data.network.model.ResponseTemplate

interface PhotoRepository {
    suspend fun getCompanyPhoto(companyId: String): ResponseTemplate<ByteArray>

    suspend fun addCompanyPhoto(photo: Uri): ResponseTemplate<Boolean>
}