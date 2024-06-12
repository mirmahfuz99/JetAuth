
package com.jetauth.auth

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetauth.auth.presentation.SignInViewModel
import com.jetauth.auth.presentation.SignInViewModelFactory

@Composable
fun SignInRoute(
    email: String?,
    onSignInSubmitted: () -> Unit,
    onSignInAsGuest: () -> Unit,
    onNavUp: () -> Unit,
) {
    val signInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory())

    SignInScreen(
        email = email,
        onSignInSubmitted = { email, password ->
            signInViewModel.signIn(email, password, onSignInSubmitted)
        },
        onSignInAsGuest = {
            signInViewModel.signInAsGuest(onSignInAsGuest)
        },
        onNavUp = onNavUp,
    )
}
