package com.kotleters.mobile.feature.company.presentation.anal.states

import com.kotleters.mobile.feature.company.domain.entity.StatisticByMonth

sealed class CompanyAnalyticsScreenState {

    data object Error : CompanyAnalyticsScreenState()

    data object Loading : CompanyAnalyticsScreenState()

    data class Content(
        val analList: List<StatisticByMonth>,
    ) : CompanyAnalyticsScreenState()
}