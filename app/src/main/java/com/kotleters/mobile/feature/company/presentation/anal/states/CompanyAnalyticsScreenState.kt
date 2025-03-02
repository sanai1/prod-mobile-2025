package com.kotleters.mobile.feature.company.presentation.anal.states

import com.kotleters.mobile.feature.company.domain.entity.StatisticByMonth

sealed class CompanyAnalyticsScreenState {

    data class Content(
        val analListState: AnalListState,
        val aiState: AIState,
    ) : CompanyAnalyticsScreenState()

    data class DetailMessage(
        val message: String,
    ) : CompanyAnalyticsScreenState()
}

sealed class AIState {

    data object Error : AIState()

    data object Loading : AIState()

    data class Content(
        val message: String,
    ) : AIState()
}

sealed class AnalListState {

    data object Error : AnalListState()

    data object Loading : AnalListState()

    data class Content(
        val list: List<StatisticByMonth>,
    ) : AnalListState()
}