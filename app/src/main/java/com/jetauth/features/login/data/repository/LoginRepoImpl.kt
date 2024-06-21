package com.jetauth.features.login.data.repository

import com.jetauth.core.api.JetAuthApi
import com.jetauth.core.db.JetAuthDatabase
import com.jetauth.features.login.data.model.LoginRequest
import retrofit2.HttpException
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(
    private val jetAuthApi: JetAuthApi,
    private val jetAuthDatabase: JetAuthDatabase,
) : LoginRepository() {
    override suspend fun login(userName: String, password: String) {
        try {
            var userPreferences =  jetAuthApi.login(LoginRequest(username = userName, password = password))
            //save to local
            jetAuthDatabase.userPreferencesDao().insert(userPreferences)
        } catch (e: HttpException){

            print("The password you entered is incorrect. Please try again or reset your password.")
        }
    }
}
