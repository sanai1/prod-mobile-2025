package com.kotleters.mobile.feature.client.presentation.add_lakuna.states

import com.kotleters.mobile.common.category.domain.CategoryInfo

sealed class AddLakunaScreenState {

    data class Content(
        val amount: Double,
        val text: String,
        val category: Pair<String, Long>,
        val subCategory: Pair<String, Long>,
        val categoryState: CategoryState,
    ) : AddLakunaScreenState()
}

sealed class CategoryState {

    data class Content(
        val categories: List<Pair<String, Long>>,
    ) : CategoryState()

    data object Loading : CategoryState()

    data object Error : CategoryState()
}
