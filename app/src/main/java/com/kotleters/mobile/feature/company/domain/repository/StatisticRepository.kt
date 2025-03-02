package com.kotleters.mobile.feature.company.domain.repository

import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.company.domain.entity.StatisticByDate
import com.kotleters.mobile.feature.company.domain.entity.StatisticByHour
import com.kotleters.mobile.feature.company.domain.entity.StatisticByMonth

interface StatisticRepository {
    suspend fun getStatisticByDate(): ResponseTemplate<List<StatisticByDate>>

    suspend fun getStatisticByHour(): ResponseTemplate<List<StatisticByHour>>

    suspend fun getStatisticByMonth(): ResponseTemplate<List<StatisticByMonth>>
}
