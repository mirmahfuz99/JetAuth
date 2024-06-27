package com.jetauth.features.profile.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.jetauth.auth.components.NameState
import com.jetauth.auth.components.TextFieldError
import com.jetauth.auth.components.TextFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstName(
    firstNameState: TextFieldState = remember { NameState() },
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface
        ),
        value = firstNameState.text,
        onValueChange = {
            firstNameState.text = it
        },

        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                firstNameState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    firstNameState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.bodyMedium,
        isError = firstNameState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        ),
        singleLine = true
    )

    firstNameState.getError()?.let { error -> TextFieldError(textError = error) }
}