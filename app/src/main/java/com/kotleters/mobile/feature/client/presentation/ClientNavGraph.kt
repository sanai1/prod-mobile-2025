package com.kotleters.mobile.feature.client.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotleters.mobile.R
import com.kotleters.mobile.common.navigation.BottomBarScreen
import com.kotleters.mobile.common.navigation.CurrentRoute
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreen

fun NavGraphBuilder.clientNavGraph(
    navController: NavHostController,
    currentRoute: String
){

    navigation(startDestination = CLIENT_MAIN, route = CLIENT_ROUTE){

        composable(CLIENT_MAIN){
            ClientMainScreen()
        }
    }
}