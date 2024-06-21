package com.jetauth.features.login.data.repository

abstract class LoginRepository {
    abstract suspend fun login(userName: String, password: String)
}