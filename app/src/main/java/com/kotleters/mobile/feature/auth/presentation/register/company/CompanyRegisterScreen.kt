package com.kotleters.mobile.feature.auth.presentation.register.company

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.annotations.Until
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.presentation.register.RegisterViewModel
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState

@Composable
fun CompanyRegisterScreen(
    back: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setupRegister(isCL = false)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding(),
    ){

        LazyColumn{
            item {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(back) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "",
                            tint = Color.White,
                            modifier = Modifier.size(70.dp))
                    }
                    Text("Регистрация", color = Color.White,
                        fontSize = 46.sp, fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(16.dp))
                }
                when(state){
                    is RegisterScreenState.Content -> {
                        when((state as RegisterScreenState.Content).userAuth){
                            is UserAuth.Client -> {
                            }
                            is UserAuth.Company -> {
                                DefaultTextField(
                                    "Имя компании",
                                    text = viewModel.companyName.value,
                                    isError = viewModel.isError.value,
                                ) {
                                    viewModel.changeCompanyName(it)
                                }
                                DefaultTextField(
                                    "Email",
                                    text = viewModel.companyEmail.value,
                                    isError = viewModel.isError.value,
                                ) {
                                    viewModel.changeCompanyEmail(it)
                                }
                                DefaultTextField(
                                    "Пароль",
                                    text = viewModel.companyPassword.value,
                                    isError = viewModel.isError.value,
                                ) {
                                    viewModel.changeCompanyPassword(it)
                                }
                                WhiteButton("Продолжить") {
                                    viewModel.onRegister()
                                }
                            }
                        }
                    }
                    RegisterScreenState.Error -> {
                        Text("Error", color = Color.White)
                    }
                    RegisterScreenState.Loading -> {

                    }
                }
            }
        }
    }

}