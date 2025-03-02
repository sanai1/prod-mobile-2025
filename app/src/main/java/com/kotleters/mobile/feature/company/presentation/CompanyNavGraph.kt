package com.kotleters.mobile.feature.company.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotleters.mobile.R
import com.kotleters.mobile.common.navigation.BottomBarScreen
import com.kotleters.mobile.feature.auth.presentation.AUTH_ROUTE
import com.kotleters.mobile.feature.company.presentation.add_offer.AddOfferScreen
import com.kotleters.mobile.feature.company.presentation.anal.CompanyAnalyticsScreen
import com.kotleters.mobile.feature.company.presentation.main.CompanyMainScreen
import com.kotleters.mobile.feature.company.presentation.main.CompanyMainViewModel
import com.kotleters.mobile.feature.company.presentation.pay.CompanyPayScreen

fun NavGraphBuilder.companyNavGraph(
    navController: NavHostController,
    companyMainViewModel: CompanyMainViewModel
) {

    navigation(startDestination = COMPANY_MAIN, route = COMPANY_ROUTE) {
        composable(COMPANY_MAIN) {

            val viewModel = hiltViewModel<CompanyMainViewModel>()

            CompanyMainScreen(
                companyMainViewModel = viewModel,
                goToAdd = { navController.navigate(COMPANY_ADD_OFFER) },
                back = {
                    navController.navigate(AUTH_ROUTE) {
                        popUpTo(COMPANY_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(COMPANY_ADD_OFFER) {
            AddOfferScreen(
                back = {
                    navController.popBackStack()
                },
                success = {
                    companyMainViewModel.fetchData()
                    navController.popBackStack()
                }
            )
        }
        composable(COMPANY_PAY) {
            CompanyPayScreen()
        }
        composable(COMPANY_ANAL) {
            CompanyAnalyticsScreen()
        }
    }
}