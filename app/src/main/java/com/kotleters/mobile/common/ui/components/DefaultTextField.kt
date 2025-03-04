package com.kotleters.mobile.common.ui.components

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.theme.secondaryGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
    placeholder: String,
    text: String,
    isError: Boolean = false,
    isPassword: Boolean = false,
    isNumber: Boolean = false,
    onChange: (String) -> Unit
) {

    OutlinedTextField(
        value = text,
        onValueChange = { onChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 32.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = secondaryGray,
            cursorColor = Color.White,
            unfocusedContainerColor = secondaryGray,
            errorContainerColor = secondaryGray
        ),
        placeholder = {
            Text(placeholder, color = Color(0xFFBABABA), fontSize = 16.sp)
        },
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle(
            color = Color.White, fontSize = 16.sp
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            capitalization = if (isPassword) KeyboardCapitalization.None else KeyboardCapitalization.Sentences,
            keyboardType = if (isPassword) KeyboardType.Password else if (isNumber) KeyboardType.Number else KeyboardType.Text
        ),
        isError = isError
    )
}