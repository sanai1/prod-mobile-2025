package com.kotleters.mobile.app

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show()
        }
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(0),
            navigationBarStyle = SystemBarStyle.dark(0)
        )
        setContent {
            AppNavigation()
        }
    }
}