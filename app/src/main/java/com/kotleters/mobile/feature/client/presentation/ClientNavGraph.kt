package com.kotleters.mobile.feature.client.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotleters.mobile.R
import com.kotleters.mobile.common.navigation.BottomBarScreen
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreen

fun NavGraphBuilder.clientNavGraph(
    navController: NavHostController
){

    val bottomBarItems = listOf(
        BottomBarScreen(
            route = CLIENT_MAIN,
            icon = R.drawable.home,
            name = "Главная"
        ),
        BottomBarScreen(
            route = CLIENT_PROFILE,
            icon = R.drawable.profile,
            name = "Профиль"
        )
    )

    navigation(startDestination = CLIENT_MAIN, route = CLIENT_ROUTE){

        composable(CLIENT_MAIN){
            ClientMainScreen()
        }
    }
}