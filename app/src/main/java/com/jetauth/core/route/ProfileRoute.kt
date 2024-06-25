package com.jetauth.core.route

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetauth.R
import com.jetauth.features.profile.presentation.pages.ProfileScreen
import com.jetauth.features.profile.presentation.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProfileRoute (){
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val isLoading by profileViewModel::isLoading

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarActionLabel = stringResource(id = R.string.dismiss)

    ProfileScreen(
        onUpdateProfileSubmit = {
            firstName, lastName ->
            CoroutineScope(Dispatchers.Main).launch {
                profileViewModel.updateProfile(firstName,lastName)
            }
        },
        isLoading = isLoading,
        snackbarHostState = snackbarHostState
    )

}