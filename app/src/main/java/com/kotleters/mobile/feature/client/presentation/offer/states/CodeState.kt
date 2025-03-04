package com.kotleters.mobile.feature.client.presentation.offer.states

import androidx.compose.ui.graphics.ImageBitmap

sealed class CodeState {

    data object Error : CodeState()

    data object Lading : CodeState()

    data class Content(
        val bitmap: ImageBitmap?
    ) : CodeState()
}