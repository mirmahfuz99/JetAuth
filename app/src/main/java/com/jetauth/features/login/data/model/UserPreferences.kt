package com.jetauth.features.login.data.model

import com.google.gson.annotations.SerializedName

data class UserPreferences(
    @SerializedName("token") val token: String?,
    @SerializedName("user_email") val userEmail: String?,
    @SerializedName("user_nicename") val userNicename: String?,
    @SerializedName("user_display_name") val userDisplayName: String?
)
