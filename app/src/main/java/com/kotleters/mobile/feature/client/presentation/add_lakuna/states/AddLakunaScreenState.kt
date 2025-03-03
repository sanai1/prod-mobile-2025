package com.kotleters.mobile.feature.client.presentation.add_lakuna.states

sealed class AddLakunaScreenState {

    data class Content(
        val amount: Int,
        val text: String,
        val category: String,
        val subCategory: String,
    )
}
