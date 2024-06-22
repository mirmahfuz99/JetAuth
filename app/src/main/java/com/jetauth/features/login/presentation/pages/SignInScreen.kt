package com.jetauth.features.login.presentation.pages

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.jetauth.R
import com.jetauth.auth.components.Email
import com.jetauth.auth.components.EmailState
import com.jetauth.auth.components.EmailStateSaver
import com.jetauth.auth.components.Password
import com.jetauth.auth.components.PasswordState
import com.jetauth.auth.components.SignInSignUpScreen
import com.jetauth.ui.theme.JetAuthTheme
import com.jetauth.ui.theme.stronglyDeemphasizedAlpha
import com.jetauth.utils.supportWideScreen
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    email: String?,
    onSignInSubmitted: (email: String, password: String) -> Unit,
    onCreateNewAccount: () -> Unit,
    onNavUp: () -> Unit,
    isLoading: Boolean,
    snackbarHostState: SnackbarHostState
) {

    val scope = rememberCoroutineScope()

    val snackbarErrorText = stringResource(id = R.string.feature_not_available)
    val snackbarActionLabel = stringResource(id = R.string.dismiss)

    Scaffold(
        content = { contentPadding ->
            SignInSignUpScreen(
                modifier = Modifier.supportWideScreen(),
                contentPadding = contentPadding,
                createNewAccount = onCreateNewAccount,
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SignInContent(
                        email = email,
                        onSignInSubmitted = onSignInSubmitted,
                        isLoading = isLoading,
                        forgotContent = {
                            TextButton(
                                onClick = {
                                    snackbarHostState.currentSnackbarData?.dismiss()
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = snackbarErrorText,
                                            actionLabel = snackbarActionLabel
                                        )
                                    }
                                },
                                modifier = Modifier.align(alignment = Alignment.End)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.forgot_password),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
                                )
                            }
                        }
                    )

                }
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        ErrorSnackbar(
            snackbarHostState = snackbarHostState,
            onDismiss = { snackbarHostState.currentSnackbarData?.dismiss() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun SignInContent(
    email: String?,
    onSignInSubmitted: (email: String, password: String) -> Unit,
    forgotContent: @Composable () -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusRequester = remember { FocusRequester() }
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState(email))
        }

        Image(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
                .height(120.dp),
            painter = painterResource(R.drawable.jetpack_compose), contentDescription = stringResource(id = R.string.jetpackCompose))

        Spacer(modifier = Modifier.height(85.dp))
        Text(
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Email(emailState, onImeAction = { focusRequester.requestFocus() })

        Spacer(modifier = Modifier.height(16.dp))

        val passwordState = remember { PasswordState() }

        val onSubmit = {
            if (emailState.isValid && passwordState.isValid) {
                onSignInSubmitted(emailState.text, passwordState.text)
            }
        }
        Password(
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSubmit() }
        )
        forgotContent()
        Spacer(modifier = Modifier.height(16.dp))
        Button(

            onClick = { onSubmit() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp),
            shape = RoundedCornerShape(10.dp),
            enabled = emailState.isValid && passwordState.isValid
        ) {
             if(isLoading){
                 CircularProgressIndicator(
                     color = MaterialTheme.colorScheme.onPrimary)
             } else {
                 Text(
                     modifier = Modifier.padding(15.dp),
                     text = stringResource(id = R.string.login)
                 )
             }
        }
    }
}

@Composable
fun ErrorSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = { }
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                content = {
                    Text(
                        text = data.visuals.message,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                action = {
                    data.visuals.actionLabel?.let {
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = stringResource(id = R.string.dismiss),
                                color = MaterialTheme.colorScheme.inversePrimary
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
    )
}




@Preview(name = "Sign in light theme", uiMode = UI_MODE_NIGHT_NO)
//@Preview(name = "Sign in dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignInPreview() {
    JetAuthTheme {

        val snackbarHostState = remember { SnackbarHostState() }

        SignInScreen(
            email = null,
            onSignInSubmitted = { _, _ -> },
            onCreateNewAccount = {},
            onNavUp = {},
            isLoading = false,
            snackbarHostState = snackbarHostState,
        )
    }
}
