package com.kotleters.mobile.feature.auth.presentation.register.company.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import com.kotleters.mobile.feature.client.presentation.add_lakuna.states.AddLakunaScreenState
import com.kotleters.mobile.feature.client.presentation.add_lakuna.states.CategoryState

@Composable
fun RegStep2(
    viewModel: RegisterViewModel,
    state: RegisterScreenState
) {

    var isCategoryMenu by remember { mutableStateOf(false) }
    var isSubMenu by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(
                vertical = 16.dp,
                horizontal = 32.dp
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(secondaryGray)
            .noRippleClickable {
                isCategoryMenu = true
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            if ((state as RegisterScreenState.Content).registerStep2.category != null) "${(state as RegisterScreenState.Content).registerStep2.category?.category}" else "Выбрать категорию",
            fontSize = 16.sp, color = Color.White
        )
        Spacer(Modifier.weight(1f))
        Icon(
            if (!isCategoryMenu) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.KeyboardArrowUp,
            "",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
    Row(
        Modifier.padding(horizontal = 16.dp)
    ) {
        DropdownMenu(
            isCategoryMenu, {
                isCategoryMenu = false
            },
            modifier = Modifier.height(300.dp),
            containerColor = secondaryGray
        ) {
            (state as RegisterScreenState.Content).categories.forEach {
                DropdownMenuItem({
                    Text(it.category, color = Color.White)
                }, {
                    viewModel.changeCategory(it)
                    isCategoryMenu = false
                })
            }
        }
    }
    if ((state as RegisterScreenState.Content).registerStep2.category != null) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = 16.dp,
                    horizontal = 32.dp
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(secondaryGray)
                .noRippleClickable {
                    isSubMenu = true
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if (state.registerStep2.underCategory != "") state.registerStep2.underCategory else "Выбрать подкатегорию",
                fontSize = 16.sp, color = Color.White
            )
            Spacer(Modifier.weight(1f))
            Icon(
                if (!isCategoryMenu) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.KeyboardArrowUp,
                "",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Row(
            Modifier.padding(horizontal = 16.dp)
        ) {
            DropdownMenu(
                isSubMenu, {
                    isSubMenu = false
                },
                modifier = Modifier.sizeIn(maxHeight = 300.dp),
                containerColor = secondaryGray
            ) {
                (state as RegisterScreenState.Content).registerStep2.category!!.subCategory.forEach {
                    DropdownMenuItem({
                        Text(it, color = Color.White)
                    }, {
                        viewModel.changeUnderCategory(it)
                        isSubMenu = false
                    })
                }
            }
        }
    }
    WhiteButton(
        "Продолжить",
        (state as RegisterScreenState.Content).registerStep2.category != null
                && state.registerStep2.underCategory != ""
    ) {
        if ((state as RegisterScreenState.Content).registerStep2.category != null
            && state.registerStep2.underCategory != ""){
            viewModel.nextStep()
        }
    }

}

