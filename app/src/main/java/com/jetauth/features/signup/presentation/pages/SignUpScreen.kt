package com.jetauth.features.signup.presentation.pages

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetauth.R
import com.jetauth.auth.components.ConfirmPasswordState
import com.jetauth.auth.components.Email
import com.jetauth.auth.components.EmailState
import com.jetauth.auth.components.EmailStateSaver
import com.jetauth.auth.components.Name
import com.jetauth.auth.components.NameState
import com.jetauth.auth.components.NameStateSaver
import com.jetauth.auth.components.Password
import com.jetauth.auth.components.PasswordState
import com.jetauth.auth.components.SignInSignUpScreen
import com.jetauth.features.login.presentation.pages.SignInScreen
import com.jetauth.ui.theme.JetAuthTheme
import com.jetauth.util.supportWideScreen


@Composable
fun SignUpScreen(
    email: String?,
    onSignUpSubmitted: (name: String, email: String, password: String) -> Unit,
    onCreateNewAccount: () -> Unit,
    onNavUp: () -> Unit,
) {


    Scaffold(
        content = { contentPadding ->
            SignInSignUpScreen(
                modifier = Modifier.supportWideScreen(),
                contentPadding = contentPadding,
                createNewAccount = onCreateNewAccount,
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SignUpContent(
                        email = email,
                        onSignUpSubmitted = onSignUpSubmitted,
                    )

                }
            }
        }
    )

}

@Composable
fun SignUpContent(
    email: String?,
    onSignUpSubmitted: (name: String, email: String, password: String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        val focusRequester = remember { FocusRequester() }
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState(email))
        }

        val nameState by rememberSaveable(stateSaver = NameStateSaver) {
            mutableStateOf(NameState())
        }
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }

        Image(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
                .height(120.dp),
            painter = painterResource(R.drawable.jetpack_compose), contentDescription = stringResource(id = R.string.fb)
        )

        //stack


        Name(nameState, onImeAction = { focusRequester.requestFocus() })
        Spacer(modifier = Modifier.height(16.dp))
        Email(emailState, onImeAction = { focusRequester.requestFocus() })

        Spacer(modifier = Modifier.height(16.dp))

        val passwordState = remember { PasswordState() }

        val onSubmit = {
            if (nameState.isValid && emailState.isValid && passwordState.isValid) {
                onSignUpSubmitted(nameState.text, emailState.text, passwordState.text)
            }
        }
        Password(
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSubmit() }
        )

        val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }
        Password(
            label = stringResource(id = R.string.confirm_password),
            passwordState = confirmPasswordState,
            onImeAction = { onSignUpSubmitted(nameState.text, emailState.text, passwordState.text) },
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
        )

        Button(

            onClick = { onSubmit() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp),
            shape = RoundedCornerShape(10.dp),
            enabled = emailState.isValid && passwordState.isValid
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = stringResource(id = R.string.signup)
            )
        }
    }
}



@Preview(name = "Sign in light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(name = "Sign in dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignUpPreview() {
    JetAuthTheme {
        SignInScreen(
            email = null,
            onSignInSubmitted = { _, _ -> },
            onCreateNewAccount = {},
            onNavUp = {},
        )
    }
}
