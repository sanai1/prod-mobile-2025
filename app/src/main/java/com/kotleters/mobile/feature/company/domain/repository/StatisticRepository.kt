package com.kotleters.mobile.feature.company.domain.repository

import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.company.domain.entity.Statistic

interface StatisticRepository {
    suspend fun getStatistic(): ResponseTemplate<List<Statistic>>
}
