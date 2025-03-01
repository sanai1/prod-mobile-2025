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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.company.presentation.add_offer.states.AddOfferScreenState

@Composable
fun AddOfferScreen(
    back: () -> Unit,
    success: () -> Unit,
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
                        DefaultTextField(
                            placeholder = "Название",
                            text = (state as AddOfferScreenState.Content).title,
                        ) {
                            viewModel.changeTitle(it)
                        }
                        DefaultTextField(
                            placeholder = "Описание",
                            text = (state as AddOfferScreenState.Content).description,
                        ) {
                            viewModel.changeDescription(it)
                        }
                        DefaultTextField(
                            placeholder = "Процент скидки",
                            text = (state as AddOfferScreenState.Content).discount.toString(),
                        ) {
                            viewModel.changeDiscount(it)
                        }
                        WhiteButton(
                            "Создать",
                            isEnabled = true
                        ) {
                            viewModel.createOffer()
                        }
                    }

                    AddOfferScreenState.Error -> {
                        Text("Error", color = Color.White)
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