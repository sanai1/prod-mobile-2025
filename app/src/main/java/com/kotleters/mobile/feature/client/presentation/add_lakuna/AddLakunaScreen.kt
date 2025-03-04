package com.kotleters.mobile.feature.client.presentation.add_lakuna

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.components.states.ErrorState
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.client.presentation.add_lakuna.states.AddLakunaScreenState
import com.kotleters.mobile.feature.client.presentation.add_lakuna.states.CategoryState

val textTypes = listOf(
    "Очень мало", "Чуть лучше", "В целом неплохо", "Уже хорошо", "Супер!"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLakunaScreen(
    back: () -> Unit,
    addLakunaScreenViewModel: AddLakunaScreenViewModel = hiltViewModel()
) {

    val state by addLakunaScreenViewModel.state.collectAsState()

    var isCategoryMenu by remember { mutableStateOf(false) }
    var isSubMenu by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton({ back() }) {
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
                when (state) {
                    is AddLakunaScreenState.Content -> {
                        when ((state as AddLakunaScreenState.Content).categoryState) {
                            is CategoryState.Content -> {
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
                                        "Перед заполнением",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
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
                                                "цене и помочь малому бизнесу развиться",
                                        fontSize = 12.sp,
                                        color = Color.White,
                                        fontStyle = FontStyle.Italic
                                    )
                                }
                                DefaultTextField(
                                    "Сумма всех трат по категории за\n" +
                                            "последние пол года ",
                                    (state as AddLakunaScreenState.Content).amount.toString(),
                                    isNumber = true
                                ) {
                                    addLakunaScreenViewModel.changeAmount(it)
                                }
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
                                        if ((state as AddLakunaScreenState.Content).category != null) "${(state as AddLakunaScreenState.Content).category?.category}" else "Выбрать категорию",
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
                                        ((state as AddLakunaScreenState.Content).categoryState as CategoryState.Content).categories.forEach {
                                            DropdownMenuItem({
                                                Text(it.category, color = Color.White)
                                            }, {
                                                addLakunaScreenViewModel.changeCategory(it)
                                                isCategoryMenu = false
                                            })
                                        }
                                    }
                                }
                                if ((state as AddLakunaScreenState.Content).category != null) {
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
                                            if ((state as AddLakunaScreenState.Content).subCategory != null) "${(state as AddLakunaScreenState.Content).subCategory}" else "Выбрать подкатегорию",
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
                                            (state as AddLakunaScreenState.Content).category!!.subCategory.forEach {
                                                DropdownMenuItem({
                                                    Text(it, color = Color.White)
                                                }, {
                                                    addLakunaScreenViewModel.changeSubCategory(it)
                                                    isSubMenu = false
                                                })
                                            }
                                        }
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(
                                            vertical = 16.dp,
                                            horizontal = 32.dp
                                        )
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(secondaryGray)
                                ) {
                                    Text(
                                        getTextClass((state as AddLakunaScreenState.Content).text).second,
                                        fontSize = 10.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .padding(top = 16.dp, bottom = 8.dp)
                                    )
                                    LinearProgressIndicator(
                                        getTextClass((state as AddLakunaScreenState.Content).text).first,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .height(5.dp)
                                            .width(150.dp),
                                        strokeCap = StrokeCap.Round,
                                        color = getTextClass((state as AddLakunaScreenState.Content).text).third,
                                        trackColor = Color(0xFF383838),

                                        )
                                    TextField(
                                        value = (state as AddLakunaScreenState.Content).text,
                                        onValueChange = {
                                            addLakunaScreenViewModel.changeText(it)
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        colors = TextFieldDefaults.colors(
                                            unfocusedContainerColor = secondaryGray,
                                            focusedContainerColor = secondaryGray,
                                            cursorColor = Color.White,
                                            unfocusedIndicatorColor = Color.White.copy(0f),
                                            focusedIndicatorColor = Color.White.copy(0f)
                                        ),
                                        placeholder = {
                                            Text(
                                                "Описание",
                                                color = Color(0xFFBABABA),
                                                fontSize = 16.sp
                                            )
                                        },
                                        shape = RoundedCornerShape(16.dp),
                                        textStyle = TextStyle(
                                            color = Color.White, fontSize = 16.sp
                                        ),
                                    )
                                }
                                WhiteButton("Готово", true) {
                                    addLakunaScreenViewModel.addLacuna()
                                    back()
                                }
                            }

                            CategoryState.Error -> {
                                ErrorState()
                            }

                            CategoryState.Loading -> {
                                ShimmerEffectCard(
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

fun getTextClass(text: String): Triple<Float, String, Color> {
    val classs = when (text.length) {
        in 0..100 -> textTypes[0]
        in 101..200 -> textTypes[1]
        in 201..300 -> textTypes[2]
        in 301..400 -> textTypes[3]
        else -> textTypes[4]
    }
    val color = when (text.length) {
        in 0..200 -> Color(0xFFB53900)
        in 201..400 -> Color(0xFFB58500)
        else -> Color(0xFF60B500)
    }
    return Triple(text.length / 500f, classs, color)
}