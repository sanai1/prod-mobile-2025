package com.kotleters.mobile.feature.auth.presentation.login

import androidx.lifecycle.ViewModel
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: UserAuthRepository
) : ViewModel(){



}