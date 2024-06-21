package com.jetauth.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetauth.features.login.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import javax.inject.Inject
@HiltViewModel
class SignInViewModel @Inject constructor (
    private val loginRepository: LoginRepository
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
        if("tokenn" == "2"){
            onSignInComplete()
        }

    }
}


