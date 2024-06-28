package com.jetauth.features.login.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jetauth.core.db.JetAuthDatabase
import com.jetauth.features.login.data.model.UserPreferences
import com.jetauth.features.login.domain.repository.LoginRepository
import com.jetauth.features.profile.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@HiltViewModel
class SignInViewModel @Inject constructor (
    private val loginRepository: LoginRepository,
    private val jetAuthDatabase: JetAuthDatabase,
): ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    private val _userPreferences: Flow<UserPreferences?> = jetAuthDatabase.userPreferencesDao().getUserPreferences()
    val userPreferences =  _userPreferences


    /**
     * Consider all sign ins successful
     */
    suspend fun signIn(
        email: String,
        password: String,
        onSignInComplete: () -> Unit,
        onError: (String) -> Unit,
    ) {

        isLoading = true
        loginRepository.login(userName = email, password = password)
        //read data from localdb from here
        _userPreferences.collect{ userPreferences ->
            if(userPreferences?.token != null){
                onSignInComplete()

            }else{
                Log.d("wrongInput","Wrong Input is provided!" )
                onError("Wrong username or password is provided!")
            }

        }
        isLoading = false
    }
}


