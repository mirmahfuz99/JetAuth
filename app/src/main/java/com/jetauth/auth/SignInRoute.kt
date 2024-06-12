
package com.jetauth.auth

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInRoute(
    email: String?,
    onSignInSubmitted: () -> Unit,
    onSignInAsGuest: () -> Unit,
    onNavUp: () -> Unit,
) {
    SignInScreen(
        email = email,
        onSignInSubmitted = { email, password ->
        },
        onSignInAsGuest = {

        },
        onNavUp = onNavUp,
    )
}
