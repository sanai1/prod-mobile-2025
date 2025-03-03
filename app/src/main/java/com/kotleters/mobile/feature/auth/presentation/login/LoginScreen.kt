package com.kotleters.mobile.feature.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.blue
import com.kotleters.mobile.common.ui.theme.lightGray
import com.kotleters.mobile.feature.auth.presentation.login.components.AnimatedPercentSymbol
import com.kotleters.mobile.feature.auth.presentation.login.states.LoginScreenState

@Composable
fun LoginScreen(
    isClient: Boolean,
    back: () -> Unit,
    goToReg: () -> Unit,
    success: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setupLogin(isClient)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding(),
    ) {
        LazyColumn {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(back) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "",
                            tint = Color.White,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                    Text(
                        "Вход", color = Color.White,
                        fontSize = 32.sp, fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(16.dp)
                    )

                }
                when (state) {
                    is LoginScreenState.Content -> {
                        DefaultTextField(
                            placeholder = "Email",
                            text = viewModel.email.value,
                            isError = (state as LoginScreenState.Content).isError
                        ) {
                            viewModel.updateEmail(it)
                        }
                        DefaultTextField(
                            placeholder = "Пароль",
                            isPassword = true,
                            text = viewModel.password.value,
                            isError = (state as LoginScreenState.Content).isError
                        ) {
                            viewModel.updatePassword(it)
                        }
                        WhiteButton(
                            "Войти",
                            isEnabled = viewModel.password.value.isNotEmpty()
                                    && viewModel.email.value.isNotEmpty()
                        ) {
                            if (viewModel.password.value.isNotEmpty()
                                && viewModel.email.value.isNotEmpty()
                            ) {
                                viewModel.onLogin()
                            }

                        }
                        Spacer(Modifier.height(64.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        fontSize = 14.sp,
                                        color = lightGray,
                                        fontWeight = FontWeight.Normal
                                    )
                                ) {
                                    append("Нет аккаунта? ")
                                }
                                withStyle(
                                    SpanStyle(
                                        fontSize = 14.sp,
                                        color = blue,
                                        fontWeight = FontWeight.Normal,
                                    )
                                ) {
                                    append("Зарегистрируйтесь")
                                }
                            }, modifier = Modifier.noRippleClickable {
                                goToReg()
                            })
                        }
                        AnimatedPercentSymbol()
                    }


                    LoginScreenState.Loading -> {
                        ShimmerEffectCard(modifier = Modifier.fillMaxSize())
                    }

                    LoginScreenState.Success -> {
                        LaunchedEffect(Unit) {
                            success()
                        }
                    }
                }
            }
        }
    }
}