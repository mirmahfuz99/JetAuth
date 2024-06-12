

package com.jetauth.auth

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetauth.auth.presentation.SignUpViewModel
import com.jetauth.auth.presentation.SignUpViewModelFactory

@Composable
fun SignUpRoute(
    email: String?,
    onSignUpSubmitted: () -> Unit,
    onSignInAsGuest:() -> Unit,
    onNavUp: () -> Unit,
) {
    val signUpViewModel: SignUpViewModel = viewModel(factory = SignUpViewModelFactory())

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
