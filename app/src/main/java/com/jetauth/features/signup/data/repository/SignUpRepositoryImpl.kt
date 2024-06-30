package com.jetauth.features.signup.data.repository

import android.util.Log
import com.jetauth.core.Result
import com.jetauth.core.api.JetAuthApi
import com.jetauth.features.signup.data.model.SignupRequest
import com.jetauth.features.signup.data.model.SignupResponse
import retrofit2.HttpException
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val jetAuthApi: JetAuthApi,
) : SignUpRepository() {
    override suspend fun signup(userName: String, password: String, email: String): Result<SignupResponse> {
        return try {
            Log.d("inside_signup", "okay")
            val response = jetAuthApi.signup(SignupRequest(username = userName, password = password, email = email))
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Error(Exception("Unexpected error: Empty response body"))
            } else {
                Log.d("response", response.message())
                Result.Error(Exception("HTTP error ${response.code()}: ${response.body()?.message}"))
            }
        } catch (e: HttpException) {
            Result.Error(Exception("Network error: ${e.message}"))
        } catch (e: Exception) {
            Result.Error(Exception("Unknown error: ${e.message}"))
        }

    }
}
