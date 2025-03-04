package com.kotleters.mobile.feature.company.presentation.anal.model

import kotlinx.serialization.Serializable

@Serializable
data class LacunaUI(
    val income: Double,
    val description: String,
    val headline: String,
)