package com.kotleters.mobile.feature.client.domain.repository

import com.kotleters.mobile.common.data.network.model.ResponseTemplate

interface ClientGenerateQRRepository {
    suspend fun clientGenerateQRRepository(offerId: String): ResponseTemplate<String>
}