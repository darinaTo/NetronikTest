package com.example.netroniktest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.netroniktest.presentation.screen.detail.UserDetailsScreen
import com.example.netroniktest.presentation.screen.list.UsersScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.USERS) {
        composable(Screen.USERS) {
            UsersScreen(
                onUserClick = { id ->
                    navController.navigate(Screen.details(id)) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(
            route = Screen.DETAILS,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) {
            UserDetailsScreen(
                onBack = { navController.navigate(Screen.USERS) }
            )
        }
    }
}
