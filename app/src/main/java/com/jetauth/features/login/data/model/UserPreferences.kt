package com.jetauth.features.login.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "user_preferences")
data class UserPreferences(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("token") val token: String?,
    @SerializedName("user_email") val userEmail: String?,
    @SerializedName("user_nicename") val userNicename: String?,
    @SerializedName("user_display_name") val userDisplayName: String?
)
