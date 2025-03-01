package com.kotleters.mobile.common.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.auth.presentation.AUTH_ON_BOARD
import com.kotleters.mobile.feature.auth.presentation.AUTH_ROUTE
import com.kotleters.mobile.feature.auth.presentation.authNavGraph

@Composable
fun AppNavigation(
    viewModel: NavigationViewModel = hiltViewModel()
) {

    val navController = rememberNavController()

    val loginState by viewModel.state.collectAsState()


    Box(
       modifier = Modifier.fillMaxSize().background(backgroundColor)
    ){
        when(loginState){
            LoginState.Loading -> {
                ShimmerEffectCard(
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {
                NavHost(
                    navController, startDestination = if (loginState is LoginState.Auth) "success" else AUTH_ROUTE,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ) {

                    authNavGraph(navController, {
                        navController.navigate("success"){
                            popUpTo(AUTH_ROUTE){
                                inclusive = true
                            }
                        }
                    })

                    composable("success") {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .systemBarsPadding()
                        ) {
                            Text("Authorized", color = Color.White)
                        }
                    }
                }
            }
        }
    }

}