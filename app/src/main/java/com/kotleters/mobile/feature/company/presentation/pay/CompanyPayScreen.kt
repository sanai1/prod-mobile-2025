package com.kotleters.mobile.feature.company.presentation.pay

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.components.states.ErrorState
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.company.presentation.pay.component.SuccessPay
import com.kotleters.mobile.feature.company.presentation.pay.states.CompanyPayScreenState

@Composable
fun CompanyPayScreen(
    viewModel: CompanyPayScreenViewModel
) {

    var scannedData by remember { mutableStateOf("") }
    var isGranted by remember { mutableStateOf(false) }

    var isSum by remember { mutableStateOf(false) }
    var sm by remember { mutableStateOf("") }

    val state by viewModel.state.collectAsState()


    RequestCameraPermission {
        isGranted = true
    }
    if (isGranted) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .systemBarsPadding()
        ) {
            TopScreenHeader("Касса")
            when (state) {
                CompanyPayScreenState.Error -> {
                    ErrorState()
                }

                CompanyPayScreenState.NotScanned -> {
                    if (!isSum) {
                        DefaultTextField("Введите сумму покупки", sm) {
                            sm = it
                        }
                        WhiteButton("Продолжить", isEnabled = true) {
                            isSum = true
                        }
                    } else {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Сканируйте QR-код клиента для\n" +
                                        "применения акции",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            if (scannedData == "") {
                                QRScannerScreen {
                                    scannedData = it
                                    viewModel.scanQR(it, sm.toDouble())

                                }
                            }
                        }
                    }
                }

                is CompanyPayScreenState.Scanned -> {
                    SuccessPay((state as CompanyPayScreenState.Scanned).scanQr) {
                        viewModel.backToScan()
                        scannedData = ""
                    }
                }

                CompanyPayScreenState.Loading -> {
                    ShimmerEffectCard(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

        }
    }
}

@Composable
fun RequestCameraPermission(
    onPermissionGranted: () -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            Toast.makeText(context, "Камера не доступна", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.CAMERA)
    }
}
