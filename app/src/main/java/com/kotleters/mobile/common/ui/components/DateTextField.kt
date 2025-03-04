package com.kotleters.mobile.common.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.theme.secondaryGray


@Composable
fun DateInputField(
    placeholder: String,
    text: String,
    onValueChange: (String) -> Unit
) {
    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text))
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { newTextFieldValue ->
            val (formattedText, newCursorPos) = formatDateInput(newTextFieldValue.text, newTextFieldValue.selection.start)
            textFieldValue = TextFieldValue(formattedText, TextRange(newCursorPos))
            onValueChange(formattedText)
        },
        placeholder = { Text("${placeholder} (ДД.ММ.ГГГГ)*") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 32.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = secondaryGray,
            cursorColor = Color.White,
            unfocusedContainerColor = secondaryGray
        ),
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle(
            color = Color.White, fontSize = 16.sp
        ),
    )
}

fun formatDateInput(input: String, cursorPos: Int): Pair<String, Int> {
    val digits = input.filter { it.isDigit() } // Оставляем только цифры
    val formatted = StringBuilder()
    var newCursorPos = cursorPos

    var digitIndex = 0
    for (i in digits.indices) {
        if (digitIndex == 2 || digitIndex == 4) {
            formatted.append('.')
            if (cursorPos > digitIndex) newCursorPos++
        }
        if (i < 8) formatted.append(digits[i])
        digitIndex++
    }

    val finalText = formatted.toString().take(10)
    newCursorPos = minOf(newCursorPos, finalText.length) // Не даем курсору выйти за границы

    return Pair(finalText, newCursorPos)
}
