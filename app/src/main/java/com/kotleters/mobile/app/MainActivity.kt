package com.kotleters.mobile.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kotleters.mobile.R
import com.kotleters.mobile.common.navigation.AppNavigation
import com.kotleters.mobile.common.navigation.BottomBarScreen
import com.kotleters.mobile.common.ui.components.BottomBar
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.auth.presentation.login.LoginScreen
import com.kotleters.mobile.feature.auth.presentation.onboard.AuthOnBoardScreen
import com.kotleters.mobile.feature.auth.presentation.register.client.ClientRegisterScreen
import com.kotleters.mobile.feature.auth.presentation.register.company.CompanyRegisterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(0),
            navigationBarStyle = SystemBarStyle.dark(0)
        )
        setContent {
            var screen by remember {
                mutableStateOf("Главная")
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .statusBarsPadding()
            ) {
                TopScreenHeader(
                    screen
                )
                Spacer(Modifier.weight(1f))
                BottomBar(
                    listOf(
                        BottomBarScreen("Главная", R.drawable.home),
                        BottomBarScreen("Оплата", R.drawable.pay)
                    ),
                    screen = screen
                ) {
                    screen = it.route
                }
            }
        }
    }
}