package com.kotleters.mobile.feature.company.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotleters.mobile.R
import com.kotleters.mobile.common.navigation.BottomBarScreen
import com.kotleters.mobile.feature.company.presentation.main.CompanyMainScreen

fun NavGraphBuilder.companyNavGraph(
    navController: NavHostController
){

    navigation(startDestination = COMPANY_MAIN, route = COMPANY_ROUTE){
        composable(COMPANY_MAIN){
            CompanyMainScreen()
        }
        composable(COMPANY_PAY){

        }
    }
}