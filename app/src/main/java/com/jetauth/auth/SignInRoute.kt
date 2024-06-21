
package com.jetauth.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetauth.features.login.data.repository.LoginRepository
import com.jetauth.features.login.presentation.viewmodel.SignInViewModel
import com.jetauth.features.login.presentation.pages.SignInScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignInRoute(
    email: String?,
    onSignInSubmitted: () -> Unit,
    onSignInAsGuest: () -> Unit,
    onNavUp: () -> Unit,
) {
    val signInViewModel: SignInViewModel = hiltViewModel()

    SignInScreen(
        email = email,
        onSignInSubmitted = { email, password ->
            CoroutineScope(Dispatchers.Main).launch {
                signInViewModel.signIn(email, password) {
                    onSignInSubmitted()
                }
            }
        },
        onSignInAsGuest = {
        },
        onNavUp = onNavUp,
    )
}
