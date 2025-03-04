package com.kotleters.mobile.feature.auth.presentation.register.company.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.lightGray
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.auth.presentation.register.RegisterViewModel
import com.kotleters.mobile.feature.auth.presentation.register.company.regSteps
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState

@Composable
fun RegStep1(
    viewModel: RegisterViewModel,
    state: RegisterScreenState
) {

    DefaultTextField("Название компании*", (state as RegisterScreenState.Content).registerStep1.title) {
        viewModel.changeCompanyName(it)
    }
    WhiteButton(
        "Продолжить",
        state.registerStep1.title.isNotEmpty()
    ) {
        if (state.registerStep1.title.isNotEmpty()){
            viewModel.nextStep()
        }
    }

}