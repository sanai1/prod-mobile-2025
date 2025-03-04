package com.kotleters.mobile.feature.auth.presentation.register.company.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.DateInputField
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.feature.auth.presentation.register.RegisterViewModel
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState

@Composable
fun RegStep3(
    viewModel: RegisterViewModel,
    state: RegisterScreenState
) {


    var percentValue by remember {
        mutableStateOf(0f)
    }

    DefaultTextField("Название*", (state as RegisterScreenState.Content).registerStep3.title) {
        viewModel.changeOfferTitle(it)
    }
    ColorChangingSlider("Процент скидки", percentValue) {
        viewModel.changeOfferPercent(it.toInt())
        percentValue = it
    }
    DefaultTextField(
        "Описание*",
        (state as RegisterScreenState.Content).registerStep3.description
    ) {
        viewModel.changeOfferDescription(it)
    }
    DateInputField(
        "Начало действия",
        (state as RegisterScreenState.Content).registerStep3.startDate
    ) {
        viewModel.changeOfferStartDate(it)
    }
    DateInputField(
        "Окончание действия",
        (state as RegisterScreenState.Content).registerStep3.endDate
    ) {
        viewModel.changeOfferEndDate(it)
    }
    WhiteButton(
        "Продолжить",
        state.registerStep3.title.isNotEmpty() && state.registerStep3.description.isNotEmpty()
                && state.registerStep3.startDate.isNotEmpty() && state.registerStep3.endDate.isNotEmpty()
    ) {
        if (state.registerStep3.title.isNotEmpty() && state.registerStep3.description.isNotEmpty()
            && state.registerStep3.startDate.isNotEmpty() && state.registerStep3.endDate.isNotEmpty()
        ) {
            viewModel.nextStep()
        }

    }

}

@Composable
fun ColorChangingSlider(
    text: String,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    val animatedColor by animateColorAsState(
        targetValue = Color.hsl(
            200f + (value / 100f) * 80f, // Диапазон от 200° (синий) до 280° (фиолетовый)
            1f,
            0.5f
        ),
        animationSpec = tween(durationMillis = 300),
        label = "slider_color"
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White.copy(0.7f)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "${(value).toInt()}%",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..100f,
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
            colors = SliderDefaults.colors(
                thumbColor = animatedColor,
                activeTrackColor = animatedColor,
                inactiveTrackColor = Color(0xFF3F3F3F)
            )
        )
    }
}