package com.jetauth.auth.components


class NameState(val name: String? = null) :
    TextFieldState(validator = ::isNameValid, errorFor = ::nameValidationError) {
    init {
        name?.let {
            text = it
        }
    }
}

/**
 * Returns an error to be displayed or null if no error was found
 */
private fun nameValidationError(email: String): String {
    return "Invalid name"
}

private fun isNameValid(email: String): Boolean {
    return email.length > 2
}

val NameStateSaver = textFieldStateSaver(NameState())
