package com.jetauth.features.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jetauth.core.db.JetAuthDatabase
import com.jetauth.features.login.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SignInViewModel @Inject constructor (
    private val loginRepository: LoginRepository,
    private val jetAuthDatabase: JetAuthDatabase,
): ViewModel() {

    /**
     * Consider all sign ins successful
     */
    suspend fun signIn(
        email: String,
        password: String,
        onSignInComplete: () -> Unit,
    ) {

        loginRepository.login(userName = email, password = password)
        //read data from localdb from here
        Log.d("token",jetAuthDatabase.userPreferencesDao().getUserPreferences()?.token.toString())
        if(jetAuthDatabase.userPreferencesDao().getUserPreferences()?.token != null){
            onSignInComplete()
        }else{
            print("outside")
        }

    }
}


