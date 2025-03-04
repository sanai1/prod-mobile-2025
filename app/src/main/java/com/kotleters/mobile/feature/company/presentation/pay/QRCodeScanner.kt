package com.kotleters.mobile.feature.company.presentation.pay

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

@Composable
fun QRScannerScreen(onResult: (String) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context) // Убрал remember

    val previewView = remember { PreviewView(context).apply {
        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
    } }

    DisposableEffect(lifecycleOwner) {
        val cameraProvider = cameraProviderFuture.get()

        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(previewView.surfaceProvider)
        }

        val imageAnalyzer = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also {
                it.setAnalyzer(ContextCompat.getMainExecutor(context), QRCodeAnalyzer { qrResult ->
                    onResult(qrResult)
                })
            }

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            cameraProvider.unbindAll()
            val camera = cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageAnalyzer)

            onDispose {
                cameraProvider.unbindAll() // Освобождаем камеру при выходе
                camera.cameraControl.enableTorch(false) // Отключаем вспышку (если она включалась)
            }
        } catch (exc: Exception) {
            Log.e("QRScanner", "Use case binding failed", exc)
        }

        onDispose {
            cameraProvider.unbindAll() // Освобождаем камеру при выходе
        }
    }

    AndroidView(
        factory = { previewView },
        modifier = Modifier.padding(16.dp).size(300.dp).clip(RoundedCornerShape(16.dp))
    )
}


class QRCodeAnalyzer(private val onQRCodeScanned: (String) -> Unit) : ImageAnalysis.Analyzer {
    private val scanner = BarcodeScanning.getClient()

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return

        val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        scanner.process(inputImage)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    barcode.rawValue?.let { result ->
                        onQRCodeScanned(result)
                        imageProxy.close()  // Закрываем, чтобы не спамило
                        return@addOnSuccessListener
                    }
                }
            }
            .addOnFailureListener {
                Log.e("QRScanner", "QR scan failed", it)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}
