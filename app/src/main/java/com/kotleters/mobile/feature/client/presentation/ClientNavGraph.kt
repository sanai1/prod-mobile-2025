package com.kotleters.mobile.feature.client.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.kotleters.mobile.feature.client.presentation.add_lakuna.AddLakunaScreen
import com.kotleters.mobile.feature.client.presentation.company.CompanyDetailScreen
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreen
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreenViewModel
import com.kotleters.mobile.feature.client.presentation.main.states.ClientMainScreenState
import com.kotleters.mobile.feature.client.presentation.offer.ClientOfferScreen
import com.kotleters.mobile.feature.client.presentation.offer.ClientOfferScreenViewModel
import com.kotleters.mobile.feature.client.presentation.profile.ClientProfileScreen
import com.kotleters.mobile.feature.client.presentation.profile.ClientProfileScreenViewModel
import com.kotleters.mobile.feature.company.presentation.COMPANY_ROUTE
import com.kotleters.mobile.feature.company.presentation.main.CompanyMainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
fun NavGraphBuilder.clientNavGraph(
    navController: NavHostController,
) {

    navigation(startDestination = CLIENT_MAIN, route = CLIENT_ROUTE) {

        composable(CLIENT_MAIN) {
            val viewModelStoreOwner = remember {
                navController.getBackStackEntry(CLIENT_ROUTE)
            }
            val clientMainScreenViewModel: ClientMainScreenViewModel =
                hiltViewModel(viewModelStoreOwner)
            ClientMainScreen(
                clientMainScreenViewModel,
                goToCompany = {
                    navController.navigate("${CLIENT_COMPANY_DETAIL}/$it")
                }
            )
        }
        composable(ADD_LAKUNA) {
            AddLakunaScreen({ navController.popBackStack() })
        }
        composable(CLIENT_PROFILE) {

            val viewModelStoreOwner = remember {
                navController.getBackStackEntry(CLIENT_ROUTE)
            }
            val clientProfileScreenViewModel: ClientProfileScreenViewModel =
                hiltViewModel(viewModelStoreOwner)

            ClientProfileScreen(
                {
                    navController.navigate(AUTH_ROUTE) {
                        popUpTo(CLIENT_ROUTE) {
                            inclusive = true
                        }
                    }
                },
                {
                    navController.navigate(ADD_LAKUNA)
                },
                viewModel = clientProfileScreenViewModel,
            )
        }
        composable("${CLIENT_COMPANY_DETAIL}/{companyIndex}",
            arguments = listOf(
                navArgument("companyIndex") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val viewModelStoreOwner = remember {
                navController.getBackStackEntry(CLIENT_ROUTE)
            }
            val clientMainScreenViewModel: ClientMainScreenViewModel =
                hiltViewModel(viewModelStoreOwner)
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
            val viewModelStoreOwner = remember {
                navController.getBackStackEntry(CLIENT_ROUTE)
            }
            val clientMainScreenViewModel: ClientMainScreenViewModel =
                hiltViewModel(viewModelStoreOwner)
            val offerIndex = it.arguments?.getInt("offerIndex") ?: 0
            val companyIndex = it.arguments?.getInt("companyIndex") ?: 0
            val offer =
                (clientMainScreenViewModel.state.value as ClientMainScreenState.Content).companies[companyIndex].discountList[offerIndex]

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