package com.kotleters.mobile.feature.client.data.network

import android.content.Context
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.client.domain.ClientGenerateQRRepository

class ClientGenerateQRRepositoryIpml(
    private val context: Context
): ClientGenerateQRRepository {
    override suspend fun ClientGenerateQRRepository(): ResponseTemplate<String> {

    }
}