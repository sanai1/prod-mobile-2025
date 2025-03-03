package com.kotleters.mobile.feature.client.presentation.add_lakuna.states

import com.kotleters.mobile.common.category.domain.CategoryInfo

sealed class AddLakunaScreenState {

    data class Content(
        val amount: Int,
        val text: String,
        val category: String,
        val subCategory: String,
        val categoryState: CategoryState,
    ) : AddLakunaScreenState()
}

sealed class CategoryState {

    data class Content(
        val categories: List<CategoryInfo>,
    ) : CategoryState()

    data object Loading : CategoryState()

    data object Error : CategoryState()
}
