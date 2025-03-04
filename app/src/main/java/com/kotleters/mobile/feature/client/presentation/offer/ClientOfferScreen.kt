package com.kotleters.mobile.feature.client.presentation.offer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kotleters.mobile.R
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.ui.components.GrayButton
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.states.ErrorState
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.lightGray
import com.kotleters.mobile.feature.client.presentation.company.components.getColorForUUID
import com.kotleters.mobile.feature.client.presentation.offer.components.QRShimmerEffect
import com.kotleters.mobile.feature.client.presentation.offer.states.CodeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientOfferScreen(
    offer: Company.Discount,
    viewModel: ClientOfferScreenViewModel,
    back: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState()

    val codeState by viewModel.codeState.collectAsState()

    var isSheet by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(isSheet) {
        if (isSheet) {
            sheetState.show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .background(getColorForUUID(offer.id))
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            IconButton(back) {
                Icon(
                    Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "",
                    tint = Color.White, modifier = Modifier.size(70.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Spacer(Modifier.weight(1f))
                Text(
                    offer.title, fontSize = 24.sp, fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Text(
                    "${(offer.discount * 10).toInt()}%",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
        Text(
            "Действует до ${offer.endDate.dayOfMonth}.${offer.endDate.month.value}.${offer.endDate.year}",
            color = lightGray, modifier = Modifier.padding(16.dp)
        )
        Text(
            offer.description, fontSize = 16.sp, fontWeight = FontWeight.Normal,
            color = Color.White, modifier = Modifier.padding(16.dp)
        )
        GrayButton("Использовать", true) {
            isSheet = true
            viewModel.generateQRCode(offer.id)
        }
    }
    if (isSheet) {
        ModalBottomSheet(
            {
                isSheet = false
            },
            containerColor = backgroundColor,
            sheetState = sheetState,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.height(500.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                when (codeState) {
                    is CodeState.Content -> {
                        (codeState as CodeState.Content).bitmap?.let {
                            Image(
                                it, "",
                                Modifier
                                    .size(300.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        }
                    }

                    CodeState.Error -> {
                        ErrorState()
                    }

                    CodeState.Lading -> {
//                        ShimmerEffectCard(
//                            modifier = Modifier.size(300.dp)
//                        )
                        QRShimmerEffect()
                    }
                }
            }
        }
    }
}