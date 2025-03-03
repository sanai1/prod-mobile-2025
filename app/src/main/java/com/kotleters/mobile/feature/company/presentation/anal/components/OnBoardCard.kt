package com.kotleters.mobile.feature.company.presentation.anal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.theme.secondaryGray

@Composable
fun OnBoardCard(
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(secondaryGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Персональный анализ улучшений",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(0.7f)
        )
        Text(
            "Мы анализируем данные потенциальных \n" +
                    "клиентов Вашего бизнеса и предлагаем лучшие\n" +
                    "решения для развития компании с помощью \n" +
                    "покрытия лакун.\n" +
                    "\n" +
                    "Лакуна - это та ниша, которую не могут покрыть\n" +
                    "крупные компании, но клиенты в ней нуждаются.\n" +
                    "Ваше предприятие сможет покрывать лакуны,\n" +
                    "переманивая клиентов к себе, тем самым \n" +
                    "увеличивая свою прибыль.",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color.White
        )
        Row {
            Spacer(Modifier.weight(1f))
            Text(
                "Например, качество булочной продукции крупной \n" +
                        "компании сильно ухудшилось. Клиент создал лакуну об\n" +
                        "этой проблеме, Вы ее увидели и с помощью системы \n" +
                        "предложений клиент узнал о Вашей компании.", fontSize = 12.sp,
                color = Color.White.copy(0.7f), textAlign = TextAlign.End,
                fontStyle = FontStyle.Italic
            )
        }
        WhiteButton("Получить информацию", true) {
            onClick()
        }
    }
}