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
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.presentation.register.RegisterViewModel
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState
import kotlinx.coroutines.launch

@Composable
fun CompanyRegisterScreen(
    back: () -> Unit,
    success: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    var byteArray by remember { mutableStateOf<ByteArray?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            viewModel.changePhoto(imageUri!!)
//            coroutineScope.launch {
////                byteArray = context.contentResolver.openInputStream(it)?.use { inputStream ->
////                    inputStream.readBytes()
////                }
////                byteArray?.let { it1 -> viewModel.changePhoto(it1) }
//            }
        }
    }

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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(back) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "",
                            tint = Color.White,
                            modifier = Modifier.size(70.dp)
                        )
                    }
                    Text(
                        "Регистрация", color = Color.White,
                        fontSize = 46.sp, fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                when (state) {
                    is RegisterScreenState.Content -> {
                        when ((state as RegisterScreenState.Content).userAuth) {
                            is UserAuth.Client -> {
                            }

                            is UserAuth.Company -> {
                                if (imageUri == null) {
                                    Box(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(secondaryGray)
                                            .noRippleClickable {
                                                launcher.launch("image/*")
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "+",
                                            fontSize = 32.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                } else {
                                    Image(
                                        rememberAsyncImagePainter(imageUri), "",
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .clip(RoundedCornerShape(16.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                DefaultTextField(
                                    "Имя компании",
                                    text = viewModel.companyName.value,
                                    isError = (state as RegisterScreenState.Content).isError,
                                ) {
                                    viewModel.changeCompanyName(it)
                                }
                                DefaultTextField(
                                    "Email",
                                    text = viewModel.companyEmail.value,
                                    isError = (state as RegisterScreenState.Content).isError,
                                ) {
                                    viewModel.changeCompanyEmail(it)
                                }
                                DefaultTextField(
                                    "Пароль",
                                    text = viewModel.companyPassword.value,
                                    isError = (state as RegisterScreenState.Content).isError,
                                    isPassword = true
                                ) {
                                    viewModel.changeCompanyPassword(it)
                                }
                                WhiteButton(
                                    "Продолжить",
                                    isEnabled = viewModel.companyName.value.isNotEmpty()
                                            && viewModel.companyEmail.value.isNotEmpty()
                                            && viewModel.companyPassword.value.isNotEmpty()
                                ) {
                                    if (viewModel.companyName.value.isNotEmpty()
                                        && viewModel.companyEmail.value.isNotEmpty()
                                        && viewModel.companyPassword.value.isNotEmpty()
                                    ) {
                                        viewModel.onRegister()
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