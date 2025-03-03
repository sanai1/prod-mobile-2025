package com.kotleters.mobile.feature.client.presentation.add_lakuna

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.secondaryGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLakunaScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton({}) {
                Icon(
                    Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "",
                    tint = Color.White,
                    modifier = Modifier.size(44.dp)
                )
            }
            Text(
                "Добавить лакуну", color = Color.White,
                fontSize = 32.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp)
            )

        }
        LazyColumn {
            item {
                Column(
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(secondaryGray)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Перед заполнением", fontSize = 16.sp, fontWeight = FontWeight.Medium,
                        color = Color.White.copy(0.7f)
                    )
                    Text(
                        "Ваше описание проблемы должно затрагивать\n" +
                                "только один аспект (к примеру, качество \n" +
                                "определенной продукции в компании)",
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        "Пожалуйста, будьте честными при заполнении, чтобы \n" +
                                "создать качественный продукт для Вас по доступной \n" +
                                "цене и помочь малому бизнесу развиться", fontSize = 12.sp,
                        color = Color.White, fontStyle = FontStyle.Italic
                    )
                }
                DefaultTextField("Сумма всех трат по категории за\n" +
                        "последние пол года ", "") { }
                DefaultTextField("Категория", "") { }
                DefaultTextField("Подкатегория", "") { }
                Column(
                    modifier = Modifier.padding(vertical = 16.dp,
                        horizontal = 32.dp).fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(secondaryGray)
                ) {
                    Text("Немного слабовато", fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp)
                            .padding(top = 16.dp, bottom = 8.dp))
                    LinearProgressIndicator(0.5f,
                        modifier = Modifier.padding(horizontal = 16.dp)
                            .height(5.dp).width(150.dp),
                        strokeCap = StrokeCap.Round,
                        color = Color(0xFFB58500),
                        trackColor = Color(0xFF383838),

                        )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {  },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = secondaryGray,
                            focusedContainerColor = secondaryGray,
                            cursorColor = Color.White,
                        ),
                        placeholder = {
                            Text("Описание", color = Color(0xFFBABABA), fontSize = 16.sp)
                        },
                        shape = RoundedCornerShape(16.dp),
                        textStyle = TextStyle(
                            color = Color.White, fontSize = 16.sp
                        ),
                    )
                }
                WhiteButton("Готово", true) { }
            }
        }
    }

}