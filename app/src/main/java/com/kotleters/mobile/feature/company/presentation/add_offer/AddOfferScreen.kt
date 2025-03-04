package com.kotleters.mobile.feature.company.presentation.add_offer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.CustomSlider
import com.kotleters.mobile.common.ui.components.DateInputField
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.components.states.ErrorState
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.auth.presentation.register.company.components.ColorChangingSlider
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState
import com.kotleters.mobile.feature.company.presentation.add_offer.states.AddOfferScreenState

@Composable
fun AddOfferScreen(
    back: () -> Unit,
    success: () -> Unit,
    isBn: Boolean,
    viewModel: AddOfferScreenViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ) {
        LazyColumn {
            item {
                IconButton(
                    back,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "",
                        tint = Color.White, modifier = Modifier.size(70.dp)
                    )
                }
            }
            item {
                when (state) {
                    is AddOfferScreenState.Content -> {
                        var percentValue by remember {
                            mutableStateOf(0f)
                        }
                        var percentValue1 by remember {
                            mutableStateOf(0f)
                        }
                        var percentValue2 by remember {
                            mutableStateOf(0f)
                        }

                        var b1 by remember {
                            mutableStateOf(0f)
                        }
                        var b2 by remember {
                            mutableStateOf(0f)
                        }

                        if (isBn){
                            CustomSlider(listOf(Pair(false, "Акция"),
                                Pair(true, "Бонус")), viewModel.isBonus.value) {
                                viewModel.isBonus.value = it as Boolean
                            }
                        }

                        if (viewModel.isBonus.value){
                            DefaultTextField(
                                "Название*",
                                (state as AddOfferScreenState.Content).title
                            ) {
                                viewModel.changeTitle(it)
                            }
                            DefaultTextField(
                                "Описание*",
                                (state as AddOfferScreenState.Content).description
                            ) {
                                viewModel.changeDescription(it)
                            }
                            DateInputField(
                                "Начало действия",
                                (state as AddOfferScreenState.Content).startDate
                            ) {
                                viewModel.changeStartDate(it)
                            }
                            DateInputField(
                                "Окончание действия",
                                (state as AddOfferScreenState.Content).endDate
                            ) {
                                viewModel.changeEndDate(it)
                            }
                            ColorChangingSlider("Клиентская выгода", percentValue1) {
                                viewModel.bonusFromPurchase.value = it.toDouble()
                                percentValue1 = it
                            }
                            ColorChangingSlider("Макс. процент оплаты бонусами", percentValue2) {
                                viewModel.bonusPaymentPercent.value = it.toDouble()
                                percentValue2 = it
                            }
                            WhiteButton(
                                "Продолжить",
                                (state as AddOfferScreenState.Content).title.isNotEmpty() && (state as AddOfferScreenState.Content).description.isNotEmpty()
                                        && (state as AddOfferScreenState.Content).startDate.isNotEmpty() && (state as AddOfferScreenState.Content).endDate.isNotEmpty()
                            ) {
                                if ((state as AddOfferScreenState.Content).title.isNotEmpty() && (state as AddOfferScreenState.Content).description.isNotEmpty()
                                    && (state as AddOfferScreenState.Content).startDate.isNotEmpty() && (state as AddOfferScreenState.Content).endDate.isNotEmpty()
                                ) {
                                    viewModel.createOffer()
                                }

                            }
                        }else{
                            DefaultTextField(
                                "Название*",
                                (state as AddOfferScreenState.Content).title
                            ) {
                                viewModel.changeTitle(it)
                            }
                            ColorChangingSlider("Процент скидки", percentValue) {
                                viewModel.offerPercent.value = it.toDouble()
                                percentValue = it
                            }
                            DefaultTextField(
                                "Описание*",
                                (state as AddOfferScreenState.Content).description
                            ) {
                                viewModel.changeDescription(it)
                            }
                            DateInputField(
                                "Начало действия",
                                (state as AddOfferScreenState.Content).startDate
                            ) {
                                viewModel.changeStartDate(it)
                            }
                            DateInputField(
                                "Окончание действия",
                                (state as AddOfferScreenState.Content).endDate
                            ) {
                                viewModel.changeEndDate(it)
                            }
                            WhiteButton(
                                "Продолжить",
                                (state as AddOfferScreenState.Content).title.isNotEmpty() && (state as AddOfferScreenState.Content).description.isNotEmpty()
                                        && (state as AddOfferScreenState.Content).startDate.isNotEmpty() && (state as AddOfferScreenState.Content).endDate.isNotEmpty()
                            ) {
                                if ((state as AddOfferScreenState.Content).title.isNotEmpty() && (state as AddOfferScreenState.Content).description.isNotEmpty()
                                    && (state as AddOfferScreenState.Content).startDate.isNotEmpty() && (state as AddOfferScreenState.Content).endDate.isNotEmpty()
                                ) {
                                    viewModel.createOffer()
                                }

                            }
                        }
                    }

                    AddOfferScreenState.Error -> {
                        ErrorState()
                    }

                    AddOfferScreenState.Success -> {
                        LaunchedEffect(Unit) {
                            success()
                        }
                    }
                }
            }
        }
    }

}