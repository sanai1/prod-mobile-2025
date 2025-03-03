package com.kotleters.mobile.feature.client.presentation.profile.states

import com.kotleters.mobile.common.domain.Lacuna
import com.kotleters.mobile.feature.client.domain.entity.LacunaClient
import com.kotleters.mobile.feature.client.domain.entity.TargetInfo


enum class ProfileSections {
    LAKUNS, BONUSES, INFO
}

sealed class ClientProfileScreenState {

    data class Content(
        val currentState: ProfileSections,
        val lakunaSectionState: LakunaSectionState,
        val bonusSectionState: BonusSectionState,
        val infoSectionState: InfoSectionState,
    ) : ClientProfileScreenState()

}

sealed class LakunaSectionState {
    data object Error : LakunaSectionState()

    data object Loading : LakunaSectionState()

    data class Content(
        val lacunas: List<LacunaClient>
    ) : LakunaSectionState()
}

sealed class BonusSectionState {

    data object Error : BonusSectionState()

    data object Loading : BonusSectionState()

    data class Content(
        val bonuses: Double,
    ) : BonusSectionState()
}

sealed class InfoSectionState {

    data object Error : InfoSectionState()

    data object Loading : InfoSectionState()

    data class Content(
        val name: String,
        val gender: TargetInfo.Gender? = null,
        val age: Int?,
    ) : InfoSectionState()
}