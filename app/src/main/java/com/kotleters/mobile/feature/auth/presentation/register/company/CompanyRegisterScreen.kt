package com.kotleters.mobile.feature.auth.presentation.register.company

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.gson.annotations.Until
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.lightGray
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.presentation.register.RegisterViewModel
import com.kotleters.mobile.feature.auth.presentation.register.company.components.RegStep1
import com.kotleters.mobile.feature.auth.presentation.register.company.components.RegStep2
import com.kotleters.mobile.feature.auth.presentation.register.company.components.RegStep3
import com.kotleters.mobile.feature.auth.presentation.register.company.components.RegStep4
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState
import kotlinx.coroutines.launch

val regSteps = listOf(
    "Заполните информацию \n" +
            "о компании",
    "Заполните информацию \n" +
            "о компании",
    "Создайте первое\n" +
            "предложение",
    "Финальный шаг до \n" +
            "получения прибыли"
)

@Composable
fun CompanyRegisterScreen(
    back: () -> Unit,
    success: () -> Unit,
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
    ) {

        LazyColumn {
            item {
                when (state) {
                    is RegisterScreenState.Content -> {
                        when ((state as RegisterScreenState.Content).userAuth) {
                            is UserAuth.Client -> {
                            }

                            is UserAuth.Company -> {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        regSteps[(state as RegisterScreenState.Content).currentRegisterStep],
                                        color = Color.White,
                                        fontSize = 46.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                                LinearProgressIndicator(
                                    progress = (state as RegisterScreenState.Content).currentRegisterStep / 4f,
                                    strokeCap = StrokeCap.Round,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .height(16.dp),
                                    color = Color(0xFF55D667),
                                    trackColor = Color(0xFF6C6C6C)
                                )
                                when ((state as RegisterScreenState.Content).currentRegisterStep) {
                                    0 -> {
                                        RegStep1(viewModel, state)
                                    }

                                    1 -> {
                                        RegStep2(viewModel, state)
                                    }

                                    2 -> {
                                        RegStep3(viewModel,state)
                                    }

                                    3 -> {
                                        RegStep4(viewModel, state)
                                    }
                                }
                            }
                        }
                    }


                    RegisterScreenState.Loading -> {
                        ShimmerEffectCard(modifier = Modifier.fillMaxSize())
                    }

                    RegisterScreenState.Success -> {
                        LaunchedEffect(Unit) {
                            success()
                        }
                    }
                }
            }
        }
    }

}