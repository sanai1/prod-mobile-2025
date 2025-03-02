package com.kotleters.mobile.feature.client.presentation.profile.states

import com.kotleters.mobile.feature.client.domain.TargetInfo

sealed class ClientProfileScreenState {

    data class Content(
        val gender: TargetInfo.Gender? = null,
        val age: Int? = null
    ) : ClientProfileScreenState()
}