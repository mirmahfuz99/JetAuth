package com.jetauth.features.login.data.datasource

import com.jetauth.features.login.data.model.UserPreferences

interface LoginDataSource {
    suspend fun login(userName: String, password: String): UserPreferences
}