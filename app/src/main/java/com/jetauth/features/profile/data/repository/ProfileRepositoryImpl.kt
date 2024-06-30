package com.jetauth.features.profile.data.repository

import android.util.Log
import com.jetauth.core.api.JetAuthApi
import com.jetauth.core.db.JetAuthDatabase
import com.jetauth.features.login.data.datasource.local.UserPreferencesDao
import com.jetauth.features.profile.data.model.ProfileRequest
import com.jetauth.features.profile.domain.ProfileRepository
import kotlinx.coroutines.delay
import retrofit2.HttpException
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val jetAuthApi: JetAuthApi,
    private val jetAuthDatabase: JetAuthDatabase
) : ProfileRepository(){
    override suspend fun updateProfile(firstName: String, lastName: String) {
        try {
            val userPreferencesDao = jetAuthDatabase.userPreferencesDao()
            val token = getTokenWithRetry(userPreferencesDao)

            if(token != null){
                val response = jetAuthApi.updateProfile(
                    authHeader ="Bearer $token",
                    ProfileRequest(
                        first_name = firstName,
                        last_name = lastName,
                    )
                )
                jetAuthDatabase.userDao().insert(response)
            }else{
                Log.d("tokenNotFound", "Token Not Found")
            }


        } catch (e:HttpException){
            jetAuthDatabase.userPreferencesDao().clear()
        }
    }

    private suspend fun getTokenWithRetry(userPreferencesDao: UserPreferencesDao): String? {
        var retries = 3
        var token: String? = null

        while (retries > 0 && token.isNullOrEmpty()) {
            token = userPreferencesDao.getUserPreferences()?.token
            if (token.isNullOrEmpty()) {
                delay(500)  // Wait for a short period before retrying
                retries--
            }
        }
        return token
    }


}