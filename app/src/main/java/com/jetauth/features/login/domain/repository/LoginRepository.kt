package com.jetauth.features.login.domain.repository

abstract class LoginRepository {
    abstract suspend fun login(userName: String, password: String)
}