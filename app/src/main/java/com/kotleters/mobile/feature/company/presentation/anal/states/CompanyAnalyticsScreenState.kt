package com.kotleters.mobile.feature.company.presentation.anal.states

import com.kotleters.mobile.feature.company.domain.entity.Statistic
import com.kotleters.mobile.feature.company.presentation.anal.model.LacunaUI

sealed class CompanyAnalyticsScreenState {

    data class Content(
        val currentState: AnalState,
        val statsState: StatsState,
        val lacunasState: LacunasState,
    ) : CompanyAnalyticsScreenState()

    data class DetailMessage(
        val message: String,
    ) : CompanyAnalyticsScreenState()
}

data class StatsState(
    val analListState: AnalListState,
    val aiState: AIState,
)

sealed class LacunasState{

    data object Error : LacunasState()

    data object Loading : LacunasState()

    data class Content(
        val lacunas: List<LacunaUI>,
    ) : LacunasState()

    data object OnBoard : LacunasState()
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
        val analytics: Statistic,
    ) : AnalListState()
}