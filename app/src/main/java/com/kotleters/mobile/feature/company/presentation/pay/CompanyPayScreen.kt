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
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.company.presentation.pay.states.CompanyPayScreenState

@Composable
fun CompanyPayScreen(
    viewModel: CompanyPayScreenViewModel = hiltViewModel()
) {

    var scannedData by remember { mutableStateOf("") }
    var isGranted by remember { mutableStateOf(false) }

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
                    Text("Error", color = Color.White)
                }

                CompanyPayScreenState.NotScanned -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (scannedData == ""){
                            QRScannerScreen {
                                scannedData = it
                                viewModel.scanQR(it)

                            }
                        }
                    }
                }

                is CompanyPayScreenState.Scanned -> {
                    Text(
                        (state as CompanyPayScreenState.Scanned).scanQr.toString(),
                        color = Color.White
                    )
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
