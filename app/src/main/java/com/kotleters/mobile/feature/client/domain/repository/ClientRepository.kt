package com.kotleters.mobile.feature.client.domain.repository

import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.feature.client.domain.entity.ClientProfile
import com.kotleters.mobile.feature.client.domain.entity.LacunaClient
import com.kotleters.mobile.feature.client.domain.entity.LacunaCreate
import com.kotleters.mobile.feature.client.domain.entity.TargetInfo

interface ClientRepository {
    suspend fun getProfile(): ResponseTemplate<ClientProfile>

    suspend fun getAllOffers(): ResponseTemplate<List<Company>>

    suspend fun updateTarget(targetInfo: TargetInfo): ResponseTemplate<Boolean>

    suspend fun createLacuna(lacunaCreate: LacunaCreate): ResponseTemplate<Boolean>

    suspend fun getLacuna(): ResponseTemplate<List<LacunaClient>>
}
