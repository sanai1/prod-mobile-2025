package com.kotleters.mobile.feature.company.presentation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotleters.mobile.R
import com.kotleters.mobile.common.navigation.BottomBarScreen
import com.kotleters.mobile.feature.auth.presentation.AUTH_ROUTE
import com.kotleters.mobile.feature.client.presentation.CLIENT_ROUTE
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreenViewModel
import com.kotleters.mobile.feature.company.presentation.add_offer.AddOfferScreen
import com.kotleters.mobile.feature.company.presentation.anal.CompanyAnalScreenViewModel
import com.kotleters.mobile.feature.company.presentation.anal.CompanyAnalyticsScreen
import com.kotleters.mobile.feature.company.presentation.main.CompanyMainScreen
import com.kotleters.mobile.feature.company.presentation.main.CompanyMainViewModel
import com.kotleters.mobile.feature.company.presentation.main.states.CompanyMainScreenState
import com.kotleters.mobile.feature.company.presentation.pay.CompanyPayScreen
import com.kotleters.mobile.feature.company.presentation.pay.CompanyPayScreenViewModel

fun NavGraphBuilder.companyNavGraph(
    navController: NavHostController,
) {

    navigation(startDestination = COMPANY_MAIN, route = COMPANY_ROUTE) {
        composable(COMPANY_MAIN) {
            val viewModelStoreOwner = remember {
                navController.getBackStackEntry(COMPANY_ROUTE)
            }
            val companyMainViewModel: CompanyMainViewModel = hiltViewModel(viewModelStoreOwner)

            CompanyMainScreen(
                companyMainViewModel = companyMainViewModel,
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
            val viewModelStoreOwner = remember {
                navController.getBackStackEntry(COMPANY_ROUTE)
            }
            val companyMainViewModel: CompanyMainViewModel = hiltViewModel(viewModelStoreOwner)
            AddOfferScreen(
                back = {
                    navController.popBackStack()
                },
                success = {
                    companyMainViewModel.fetchData()
                    navController.popBackStack()
                },
                isBn = true,
            )
        }
        composable(COMPANY_PAY) {
            val viewModelStoreOwner = remember {
                navController.getBackStackEntry(COMPANY_ROUTE)
            }
            val companyPayScreenViewModel: CompanyPayScreenViewModel =
                hiltViewModel(viewModelStoreOwner)
            CompanyPayScreen(companyPayScreenViewModel)
        }
        composable(COMPANY_ANAL) {
            val viewModelStoreOwner = remember {
                navController.getBackStackEntry(COMPANY_ROUTE)
            }
            val companyAnalScreenViewModel: CompanyAnalScreenViewModel =
                hiltViewModel(viewModelStoreOwner)
            CompanyAnalyticsScreen(companyAnalScreenViewModel)
        }
    }
}