package com.kotleters.mobile.common.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.kotleters.mobile.R
import com.kotleters.mobile.common.ui.components.BottomBar
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.auth.presentation.AUTH_ON_BOARD
import com.kotleters.mobile.feature.auth.presentation.AUTH_ROUTE
import com.kotleters.mobile.feature.auth.presentation.authNavGraph
import com.kotleters.mobile.feature.client.presentation.CLIENT_MAIN
import com.kotleters.mobile.feature.client.presentation.CLIENT_PROFILE
import com.kotleters.mobile.feature.client.presentation.CLIENT_ROUTE
import com.kotleters.mobile.feature.client.presentation.clientNavGraph
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreenViewModel
import com.kotleters.mobile.feature.company.presentation.COMPANY_ANAL
import com.kotleters.mobile.feature.company.presentation.COMPANY_MAIN
import com.kotleters.mobile.feature.company.presentation.COMPANY_PAY
import com.kotleters.mobile.feature.company.presentation.COMPANY_ROUTE
import com.kotleters.mobile.feature.company.presentation.companyNavGraph
import com.kotleters.mobile.feature.company.presentation.main.CompanyMainViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    viewModel: NavigationViewModel = hiltViewModel()
) {

    val navController = rememberNavController()

    val loginState by viewModel.state.collectAsState()

    val currentRoute = CurrentRoute(navController)

    val clientBottomBarItems = listOf(
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

    val companyBottomBarItems = listOf(
        BottomBarScreen(
            route = COMPANY_MAIN,
            icon = R.drawable.company,
            name = "Компания"
        ),
        BottomBarScreen(
            route = COMPANY_PAY,
            icon = R.drawable.pay,
            name = "Касса"
        ),
        BottomBarScreen(
            name = "Аналитика",
            route = COMPANY_ANAL,
            icon = R.drawable.anal
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        when (loginState) {
            LoginState.Loading -> {
                Column (
                    Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ){  }
            }

            else -> {
                Scaffold(
                    bottomBar = {
                        if (currentRoute in clientBottomBarItems.map { it.route } + companyBottomBarItems.map { it.route }) {
                            BottomBar(
                                if (currentRoute in clientBottomBarItems.map { it.route }) clientBottomBarItems else companyBottomBarItems,
                                screen = currentRoute ?: ""
                            ) {
                                navController.navigate(it.route)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ) { pd ->
                    NavHost(
                        navController,
                        enterTransition = { slideInHorizontally(initialOffsetX = { 300 }) + fadeIn() },
                        exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { -300 }) },
                        startDestination = when (loginState) {
                            LoginState.AuthClient -> CLIENT_ROUTE
                            LoginState.AuthCompany -> COMPANY_ROUTE
                            LoginState.Loading -> CLIENT_ROUTE
                            LoginState.NotAuth -> AUTH_ROUTE
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor)
                            .padding(bottom = if (currentRoute in clientBottomBarItems.map { it.route } + companyBottomBarItems.map { it.route }) 90.dp else 0.dp),
                    ) {

                        authNavGraph(
                            navController, {
                                navController.navigate(CLIENT_ROUTE) {
                                    popUpTo(AUTH_ROUTE) {
                                        inclusive = true
                                    }
                                }
                            },
                            companySuccess = {
                                navController.navigate(COMPANY_ROUTE) {
                                    popUpTo(AUTH_ROUTE) {
                                        inclusive = true
                                    }
                                }
                            }
                        )

                        clientNavGraph(
                            navController
                        )

                        companyNavGraph(navController)
                    }
                }
            }
        }
    }

}