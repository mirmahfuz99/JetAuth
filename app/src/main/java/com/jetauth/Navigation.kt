package com.jetauth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetauth.Destinations.HOME_ROUTE
import com.jetauth.Destinations.SIGN_IN_ROUTE
import com.jetauth.Destinations.SIGN_UP_ROUTE
import com.jetauth.auth.SignInRoute


object Destinations {
    const val SIGN_UP_ROUTE = "signup/{email}"
    const val SIGN_IN_ROUTE = "signin"
    const val HOME_ROUTE = "home"
}

@Composable
fun JetAuthNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = SIGN_IN_ROUTE,
    ) {

        composable(SIGN_IN_ROUTE) {
            val startingEmail = it.arguments?.getString("email")
            SignInRoute(
                email = startingEmail,
                onSignInSubmitted = {
                    navController.navigate(HOME_ROUTE)
                },
                onSignInAsGuest = {
                    navController.navigate(HOME_ROUTE)
                },
                onNavUp = navController::navigateUp,
            )
        }

    }
}
