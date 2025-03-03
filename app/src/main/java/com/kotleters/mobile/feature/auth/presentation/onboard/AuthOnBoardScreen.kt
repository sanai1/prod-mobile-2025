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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.R
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor

@Composable
fun AuthOnBoardScreen(
    asClient: () -> Unit,
    asCompany: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ){

        Column {
            TopScreenHeader("Авторизация")
            Spacer(Modifier.weight(1f))
            Row {
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                16.dp,
                                0.dp, 16.dp, 0.dp
                            )
                        )
                        .background(Color.White.copy())
                        .noRippleClickable { asClient() }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(ImageVector.vectorResource(R.drawable.profile), "",
                        modifier = Modifier.size(36.dp))
                    Text("Продолжить как\nклиент", fontSize = 24.sp, fontWeight = FontWeight.Medium,
                        color = Color.Black, textAlign = TextAlign.Center)
                    IconButton({  asClient()}) {
                        Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, "",
                            modifier = Modifier.size(48.dp), tint = Color.Black)
                    }
                }
            }
            Row {
                Row(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                16.dp,
                                0.dp, 16.dp, 0.dp
                            )
                        )
                        .background(backgroundColor)
                        .noRippleClickable { asCompany() }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(ImageVector.vectorResource(R.drawable.company), "",
                        modifier = Modifier.size(36.dp), tint = Color.White)
                    Text("Продолжить как\n" +
                            "компания", fontSize = 24.sp, fontWeight = FontWeight.Medium,
                        color = Color.White, textAlign = TextAlign.Center)
                    IconButton({ asCompany() }) {
                        Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, "",
                            modifier = Modifier.size(48.dp), tint = Color.White)
                    }
                }
                Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.weight(1f))
            Row (
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text("Offeria ©\n2025", color = Color.White,
                    fontSize = 12.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
            }
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
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