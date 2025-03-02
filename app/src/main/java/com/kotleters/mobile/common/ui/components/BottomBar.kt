package com.kotleters.mobile.common.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.navigation.BottomBarScreen
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.lightGray

@Composable
fun BottomBar(
    screens: List<BottomBarScreen>,
    screen: String,
    onChange: (BottomBarScreen) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            .background(Color.Black)
            .navigationBarsPadding()
            .padding(vertical = 16.dp, horizontal = 25.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                ImageVector.vectorResource(screens.first { it.route == screen }.icon), "",
                Modifier.size(36.dp), tint = Color.White
            )
            Spacer(Modifier.width(8.dp))
            Text(
                screens.first { it.route == screen }.name,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
        screens.filter { it.route != screen }.forEach {
            Icon(ImageVector.vectorResource(it.icon), "",
                tint = lightGray, modifier = Modifier.size(36.dp).noRippleClickable {
                    onChange(it)
                })
        }
    }
}