package com.kotleters.mobile.feature.auth.presentation

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotleters.mobile.feature.auth.presentation.login.LoginScreen
import com.kotleters.mobile.feature.auth.presentation.onboard.AuthOnBoardScreen
import com.kotleters.mobile.feature.auth.presentation.register.client.ClientRegisterScreen
import com.kotleters.mobile.feature.auth.presentation.register.company.CompanyRegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController,
                                 clientSuccess: () -> Unit,
                                 companySuccess: () -> Unit) {

    navigation(startDestination = AUTH_ON_BOARD, route = AUTH_ROUTE) {

        composable(AUTH_ON_BOARD) {
            AuthOnBoardScreen({
                navController.navigate(USER_LOGIN)
            }) {
                navController.navigate(COMPANY_LOGIN)
            }
        }
        composable(USER_LOGIN) {
            LoginScreen(
                isClient = true, goToReg = { navController.navigate(USER_REG) },
                success = { clientSuccess() },
                back = { navController.popBackStack() }
            )
        }
        composable(COMPANY_LOGIN) {
            LoginScreen(
                isClient = false,
                goToReg = { navController.navigate(COMPANY_REG) },
                success = { companySuccess() },
                back = { navController.popBackStack() }
            )
        }

        composable(COMPANY_REG) {
            CompanyRegisterScreen(
                back = { navController.popBackStack() },
                success = { companySuccess() }
            )
        }
        composable(USER_REG) {
            ClientRegisterScreen(
                back = { navController.popBackStack() },
                success = { clientSuccess() }
            )
        }
    }
}