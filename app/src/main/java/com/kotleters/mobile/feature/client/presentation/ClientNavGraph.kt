package com.kotleters.mobile.feature.client.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotleters.mobile.R
import com.kotleters.mobile.common.navigation.BottomBarScreen
import com.kotleters.mobile.common.navigation.CurrentRoute
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreen
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreenViewModel
import com.kotleters.mobile.feature.client.presentation.profile.ClientProfileScreen

fun NavGraphBuilder.clientNavGraph(
    navController: NavHostController,
    currentRoute: String,
    clientMainScreenViewModel: ClientMainScreenViewModel
){

    navigation(startDestination = CLIENT_MAIN, route = CLIENT_ROUTE){



        composable(CLIENT_MAIN){

            ClientMainScreen(clientMainScreenViewModel)
        }
        composable(CLIENT_PROFILE){
            ClientProfileScreen()
        }
    }
}