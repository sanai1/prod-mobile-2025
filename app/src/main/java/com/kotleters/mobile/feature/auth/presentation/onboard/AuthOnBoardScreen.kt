package com.kotleters.mobile.feature.auth.presentation.onboard

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.R
import com.kotleters.mobile.common.ui.theme.backgroundColor

@Composable
fun AuthOnBoardScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ){

        Column {
            Text("Авторизация", color = Color.White,
                fontSize = 46.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp))
            Spacer(Modifier.weight(1f))
            AuthButton(
                text = "Продолжить как\nклиент",
                pic = R.drawable.profile
            ) { }
            AuthButton(
                text = "Продолжить как\nкомпания",
                pic = R.drawable.company
            ) { }
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun AuthButton(
    text: String,
    pic: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) { 
        Image(ImageVector.vectorResource(pic), "",
            modifier = Modifier.size(36.dp))
        Text(text, fontSize = 24.sp, fontWeight = FontWeight.Medium,
            color = Color.White)
        IconButton(onClick) {
            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, "",
                modifier = Modifier.size(48.dp), tint = Color.White)
        }
    }
}