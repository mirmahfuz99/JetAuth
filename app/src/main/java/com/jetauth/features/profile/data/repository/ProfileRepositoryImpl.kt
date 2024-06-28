package com.jetauth.features.profile.data.repository

import android.util.Log
import com.jetauth.core.api.JetAuthApi
import com.jetauth.core.db.JetAuthDatabase
import com.jetauth.features.profile.data.model.ProfileRequest
import com.jetauth.features.profile.domain.ProfileRepository
import retrofit2.HttpException
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val jetAuthApi: JetAuthApi,
    private val jetAuthDatabase: JetAuthDatabase
) : ProfileRepository(){
    override suspend fun updateProfile(firstName: String, lastName: String) {
        try {
            val params = mapOf(
                "first_name" to firstName,
                "last_name" to lastName
            )
            val response = jetAuthApi.updateProfile(params = params)
            jetAuthDatabase.userDao().insert(response)

        } catch (e:HttpException){
            jetAuthDatabase.userPreferencesDao().clear()
        }
    }


}