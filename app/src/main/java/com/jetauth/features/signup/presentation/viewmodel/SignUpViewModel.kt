package com.jetauth.features.signup.presentation.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import com.jetauth.core.Result
import com.jetauth.features.signup.data.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    /**
     * Consider all sign ups successful
     */
    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        onSignUpComplete: () -> Unit,
    ) {
        when(val userPreferences = signUpRepository.signup(userName = name,password = password, email = email)){
            is Result.Success -> {
                Log.d("signup","Signup success")
                if(userPreferences.data.code == 200){
                    onSignUpComplete()
                }
            }
            is Result.Error -> {
                Log.d("signup",userPreferences.exception.message.toString())
            }
        }

    }
}
