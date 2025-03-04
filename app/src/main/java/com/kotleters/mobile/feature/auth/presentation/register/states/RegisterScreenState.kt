package com.kotleters.mobile.feature.auth.presentation.register.states

import com.kotleters.mobile.common.category.domain.CategoryInfo
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.company.presentation.add_offer.states.CategoryUI
import java.time.LocalDateTime

sealed class RegisterScreenState {

    data object Loading : RegisterScreenState()

    data object Success : RegisterScreenState()


    data class Content(
        val currentRegisterStep: Int,
        val registerStep1: RegisterStep1,
        val registerStep2: RegisterStep2,
        val registerStep3: RegisterStep3,
        val registerStep4: RegisterStep4,
        val isError: Boolean,
        val userAuth: UserAuth,
        val categories: List<CategoryUI>
    ) : RegisterScreenState()
}

data class RegisterStep4(
    val email: String,
    val password: String,
    val passwordAgain: String,
)

data class RegisterStep3(
    val title: String,
    val percent: Int,
    val description: String,
    val startDate: String,
    val endDate: String
)

data class RegisterStep2(
    val category: CategoryUI?,
    val underCategory: String,
)

data class RegisterStep1(
    val photo: ByteArray?,
    val title: String
)