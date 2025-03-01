package com.kotleters.mobile.feature.company.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.kotleters.mobile.R
import com.kotleters.mobile.common.navigation.BottomBarScreen

fun NavGraphBuilder.companyNavGraph(
    navController: NavHostController
){

    val bottomBarItems = listOf(
        BottomBarScreen(
            route = COMPANY_MAIN,
            icon = R.drawable.company,
            name = "Компания"
        ),
        BottomBarScreen(
            route = COMPANY_PAY,
            icon = R.drawable.pay,
            name = "Касса"
        )
    )

    navigation(startDestination = COMPANY_MAIN, route = COMPANY_ROUTE){

    }
}