package com.kotleters.mobile.feature.client.presentation.add_lakuna.states

import com.kotleters.mobile.common.category.domain.CategoryInfo
import com.kotleters.mobile.feature.company.presentation.add_offer.states.CategoryUI

sealed class AddLakunaScreenState {

    data class Content(
        val amount: String,
        val text: String,
        val category: CategoryUI?,
        val subCategory: String?,
        val categoryState: CategoryState,
    ) : AddLakunaScreenState()
}

sealed class CategoryState {

    data class Content(
        val categories: List<CategoryUI>,
    ) : CategoryState()

    data object Loading : CategoryState()

    data object Error : CategoryState()
}
