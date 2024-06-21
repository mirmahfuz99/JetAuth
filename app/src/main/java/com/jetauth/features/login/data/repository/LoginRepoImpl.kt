package com.jetauth.features.login.data.repository

import com.jetauth.core.api.JetAuthApi
import com.jetauth.features.login.data.datasource.LoginDataSource
import com.jetauth.features.login.data.model.LoginRequest
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(
    private val jetAuthApi: JetAuthApi
) : LoginRepository() {
    override suspend fun login(userName: String, password: String) {
        jetAuthApi.login(LoginRequest(username = userName, password = password))
    }
}
