package com.jetauth.core.api

import com.jetauth.features.login.data.model.LoginRequest
import com.jetauth.features.login.data.model.UserPreferences
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JetAuthApi {

    @POST("/wp-json/jwt-auth/v1/token")
    suspend fun login(@Body request: LoginRequest): UserPreferences

}