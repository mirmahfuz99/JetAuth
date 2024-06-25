package com.jetauth.features.profile.domain

abstract class ProfileRepository {
    abstract suspend fun updateProfile(firstName: String, lastName: String)
}