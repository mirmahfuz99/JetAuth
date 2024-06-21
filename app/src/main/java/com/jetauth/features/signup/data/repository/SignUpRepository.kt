package com.jetauth.features.signup.data.repository

import com.jetauth.core.Result
import com.jetauth.features.signup.data.model.SignupResponse

abstract class SignUpRepository {
    abstract suspend fun signup(userName: String, password: String, email: String): Result<SignupResponse>
}