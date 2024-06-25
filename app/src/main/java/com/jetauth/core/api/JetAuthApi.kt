package com.jetauth.core.api

import com.jetauth.features.login.data.model.LoginRequest
import com.jetauth.features.login.data.model.UserPreferences
import com.jetauth.features.profile.data.model.ProfileRequest
import com.jetauth.features.profile.data.model.User
import com.jetauth.features.signup.data.model.SignupRequest
import com.jetauth.features.signup.data.model.SignupResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface JetAuthApi {

    @POST("/wp-json/jwt-auth/v1/token")
    suspend fun login(@Body request: LoginRequest): UserPreferences

    @POST("wp-json/wp/v2/users/register")
    suspend fun signup(@Body request: SignupRequest): Response<SignupResponse>

    @POST("/wp-json/wp/v2/users/me")
    suspend fun updateProfile(@Body request: ProfileRequest): User

}