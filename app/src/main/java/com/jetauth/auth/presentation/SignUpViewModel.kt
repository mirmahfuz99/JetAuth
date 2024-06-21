package com.jetauth.auth.presentation
import androidx.lifecycle.ViewModel
import com.jetauth.auth.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    /**
     * Consider all sign ups successful
     */
    fun signUp(
        name: String,
        email: String,
        password: String,
        onSignUpComplete: () -> Unit,
    ) {
        userRepository.signUp(name, email, password)
        onSignUpComplete()
    }

    fun signInAsGuest(
        onSignInComplete: () -> Unit,
    ) {
        userRepository.signInAsGuest()
        onSignInComplete()
    }
}
