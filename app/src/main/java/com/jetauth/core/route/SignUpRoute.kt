package com.jetauth.core.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetauth.features.signup.presentation.pages.SignUpScreen
import com.jetauth.features.signup.presentation.viewmodel.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    email: String?,
    onSignUpSubmitted: () -> Unit,
    onCreateNewAccount:() -> Unit,
    onNavUp: () -> Unit,
) {
    val signUpViewModel: SignUpViewModel = hiltViewModel()

    SignUpScreen(
        email = email,
        onSignUpSubmitted = {name, email, password ->
                            CoroutineScope(Dispatchers.Main).launch {
                                signUpViewModel.signUp(
                                    name = name,
                                    email = email,
                                    password = password,
                                    onSignUpComplete = onSignUpSubmitted
                                )
                            }
        },
        onCreateNewAccount = onCreateNewAccount,
        onNavUp = onNavUp,
    )
}
