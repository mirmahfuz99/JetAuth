

package com.jetauth.auth

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpRoute(
    email: String?,
    onSignUpSubmitted: () -> Unit,
    onSignInAsGuest:() -> Unit,
    onNavUp: () -> Unit,
) {
    SignUpScreen(
        email = email,
        onSignUpSubmitted = {name, email, password ->

        },
        onSignInAsGuest = {
        },
        onNavUp = onNavUp,
    )
}
