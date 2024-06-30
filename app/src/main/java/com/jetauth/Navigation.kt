package com.jetauth
import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetauth.Destinations.MAIN_ROUTE
import com.jetauth.Destinations.SIGN_IN_ROUTE
import com.jetauth.Destinations.SIGN_UP_ROUTE
import com.jetauth.core.route.MainRoute
import com.jetauth.core.route.SignInRoute
import com.jetauth.core.route.SignUpRoute
import com.jetauth.features.login.presentation.viewmodel.SignInViewModel
import kotlin.math.sign

object Destinations {
    const val SIGN_UP_ROUTE = "signup"
    const val SIGN_IN_ROUTE = "signin"
    const val MAIN_ROUTE = "main"
}

@Composable
fun JetAuthNavHost(
    navController: NavHostController = rememberNavController(),
    signInViewModel: SignInViewModel
) {

    val userPreferences by signInViewModel.userPreferences.observeAsState()

    Log.d("token_check", userPreferences?.token.toString())
    // Once user preferences are loaded, determine the start destination
    val startDestination = if (userPreferences?.token.isNullOrEmpty()) {
        SIGN_IN_ROUTE
    } else {
        MAIN_ROUTE
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(SIGN_IN_ROUTE) {
            val startingEmail = it.arguments?.getString("email")
            SignInRoute(
                email = startingEmail,
                onSignInSubmitted = {
                    //read data from local storage
                    navController.navigate(MAIN_ROUTE)
                },
                onCreateNewAccount = {
                    navController.navigate(SIGN_UP_ROUTE)
                },
                onNavUp = navController::navigateUp,
            )
        }

        composable(SIGN_UP_ROUTE) {
            val startingEmail = it.arguments?.getString("email")
            SignUpRoute(
                email = startingEmail,
                onSignUpSubmitted = {
                    navController.navigate(SIGN_IN_ROUTE)
                },
                onCreateNewAccount = {
                    navController.navigate(MAIN_ROUTE)
                },
                onNavUp = navController::navigateUp,
            )
        }


        composable(MAIN_ROUTE) {
            MainRoute()
        }
    }
}
