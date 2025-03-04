package com.kotleters.mobile.feature.company.presentation.add_offer.states

sealed class AddOfferScreenState {

    data object Error : AddOfferScreenState()

    data class Content(
        val title: String,
        val description: String,
        val discount: Double,
        val startDate: String,
        val endDate: String,
    ) : AddOfferScreenState()

    data object Success : AddOfferScreenState()
}

data class CategoryUI(
    val category: String,
    val subCategory: List<String>,
    val id: Long,
)