package com.kotleters.mobile.feature.client.presentation.main.states

import com.kotleters.mobile.common.domain.Company

sealed class ClientMainScreenState {

    data class Content(
        val companies: List<Company>,
    ) : ClientMainScreenState()

    data object Loading : ClientMainScreenState()

    data class Error(val message: String,) : ClientMainScreenState()
}