package com.jetauth.features.login.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jetauth.features.login.data.model.UserPreferences

@Dao
interface UserPreferencesDao {
    @Insert
    suspend fun insert(userPreferences: UserPreferences)

    @Query("SELECT * FROM user_preferences LIMIT 1")
    suspend fun getUserPreferences(): UserPreferences?

    @Query("DELETE FROM user_preferences")
    suspend fun clear()

    @Delete
    suspend fun delete(userPreferences: UserPreferences)
}