package com.jetauth.features.login.data.datasource

import com.jetauth.core.api.JetAuthApi
import com.jetauth.features.login.data.model.LoginRequest
import com.jetauth.features.login.data.model.UserPreferences
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val api: JetAuthApi
) : LoginDataSource {
    override suspend fun login(userName: String, password: String): UserPreferences {
        return api.login(LoginRequest(username = userName, password = password))
    }
}