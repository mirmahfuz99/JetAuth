

package com.jetauth.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetauth.auth.presentation.SignUpViewModel

@Composable
fun SignUpRoute(
    email: String?,
    onSignUpSubmitted: () -> Unit,
    onSignInAsGuest:() -> Unit,
    onNavUp: () -> Unit,
) {
    val signUpViewModel: SignUpViewModel = hiltViewModel()

    SignUpScreen(
        email = email,
        onSignUpSubmitted = {name, email, password ->
                            signUpViewModel.signUp(name, email, password, onSignUpSubmitted)
        },
        onSignInAsGuest = {
            signUpViewModel.signInAsGuest(onSignInAsGuest)
        },
        onNavUp = onNavUp,
    )
}
