package com.jetauth.features.profile.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jetauth.core.db.JetAuthDatabase
import com.jetauth.features.profile.domain.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val jetAuthDatabase: JetAuthDatabase,
): ViewModel(){

    var isLoading by mutableStateOf(false)
        private set

    suspend fun updateProfile(
        firstName: String,
        lastName: String
    ){
        isLoading = true
        profileRepository.updateProfile(firstName,lastName)
        isLoading = false

    }
}