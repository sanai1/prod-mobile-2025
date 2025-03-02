package com.kotleters.mobile.feature.client.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.kotleters.mobile.R
import com.kotleters.mobile.common.navigation.BottomBarScreen
import com.kotleters.mobile.common.navigation.CurrentRoute
import com.kotleters.mobile.feature.auth.presentation.AUTH_ROUTE
import com.kotleters.mobile.feature.client.presentation.company.CompanyDetailScreen
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreen
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreenViewModel
import com.kotleters.mobile.feature.client.presentation.main.states.ClientMainScreenState
import com.kotleters.mobile.feature.client.presentation.offer.ClientOfferScreen
import com.kotleters.mobile.feature.client.presentation.offer.ClientOfferScreenViewModel
import com.kotleters.mobile.feature.client.presentation.profile.ClientProfileScreen

fun NavGraphBuilder.clientNavGraph(
    navController: NavHostController,
    clientMainScreenViewModel: ClientMainScreenViewModel
) {

    navigation(startDestination = CLIENT_MAIN, route = CLIENT_ROUTE) {

        composable(CLIENT_MAIN) {

            ClientMainScreen(
                hiltViewModel<ClientMainScreenViewModel>(),
                goToCompany = {
                    navController.navigate("${CLIENT_COMPANY_DETAIL}/$it")
                }
            )
        }
        composable(CLIENT_PROFILE) {
            ClientProfileScreen({
                navController.navigate(AUTH_ROUTE) {
                    popUpTo(CLIENT_ROUTE) {
                        inclusive = true
                    }
                }
            })
        }
        composable("${CLIENT_COMPANY_DETAIL}/{companyIndex}",
            arguments = listOf(
                navArgument("companyIndex") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val companyIndex = backStackEntry.arguments?.getInt("companyIndex") ?: 0
            val companies =
                (clientMainScreenViewModel.state.value as ClientMainScreenState.Content).companies

            CompanyDetailScreen(
                companies[companyIndex],
                onOfferClick = {
                    navController.navigate("${CLIENT_OFFER_DETAIL}/$it/$companyIndex")
                },
                back = { navController.popBackStack() }
            )
        }
        composable("${CLIENT_OFFER_DETAIL}/{offerIndex}/{companyIndex}",
            arguments = listOf(
                navArgument("offerIndex") {
                    type = NavType.IntType
                },
                navArgument("companyIndex") {
                    type = NavType.IntType
                }
            )) {
            val offerIndex = it.arguments?.getInt("offerIndex") ?: 0
            val companyIndex = it.arguments?.getInt("companyIndex") ?: 0
            val offer =
                (clientMainScreenViewModel.state.value as ClientMainScreenState.Content).companies[companyIndex].offers[offerIndex]

            val viewModel = hiltViewModel<ClientOfferScreenViewModel>()

            ClientOfferScreen(
                offer,
                viewModel = viewModel
            ) {
                navController.popBackStack()
            }
        }
    }
}