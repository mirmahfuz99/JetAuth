package com.jetauth.core.route

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetauth.R
import com.jetauth.features.login.presentation.viewmodel.SignInViewModel
import com.jetauth.features.login.presentation.pages.SignInScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignInRoute(
    email: String?,
    onSignInSubmitted: () -> Unit,
    onCreateNewAccount: () -> Unit,
    onNavUp: () -> Unit,

) {
    val signInViewModel: SignInViewModel = hiltViewModel()
    val isLoading by signInViewModel::isLoading

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarActionLabel = stringResource(id = R.string.dismiss)


    SignInScreen(
        email = email,
        onSignInSubmitted = { email, password ->
            CoroutineScope(Dispatchers.Main).launch {
                signInViewModel.signIn(
                    email = email,
                    password = password,
                    onSignInComplete = {
                        onSignInSubmitted()
                    },
                    onError = { message ->
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = message,
                                actionLabel = snackbarActionLabel
                            )
                        }
                    }
                )
            }
        },
        onCreateNewAccount = onCreateNewAccount,
        onNavUp = onNavUp,
        isLoading = isLoading,
        snackbarHostState = snackbarHostState)
}
