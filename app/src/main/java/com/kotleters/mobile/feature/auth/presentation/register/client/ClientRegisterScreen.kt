package com.kotleters.mobile.feature.auth.presentation.register.client

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.feature.auth.presentation.register.RegisterViewModel

@Composable
fun ClientRegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    val state by registerViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        registerViewModel.setupRegister(isCL = true)
    }

    DefaultTextField("Name", "") { }
}