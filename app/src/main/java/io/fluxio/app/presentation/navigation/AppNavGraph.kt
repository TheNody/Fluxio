package io.fluxio.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.fluxio.app.presentation.ui.screens.home.screen.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: Screen = Screen.Home
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}
