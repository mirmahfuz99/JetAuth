package com.jetauth.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jetauth.features.login.data.datasource.local.UserPreferencesDao
import com.jetauth.features.login.data.model.UserPreferences
import com.jetauth.features.profile.data.datasource.local.UserDao
import com.jetauth.features.profile.data.model.User

@Database(
    entities = [
        UserPreferences::class,
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class JetAuthDatabase: RoomDatabase() {

    abstract fun userPreferencesDao(): UserPreferencesDao
    abstract fun userDao(): UserDao

    companion object{
        const val DATABASE_NAME = "jet_auth_db"
    }
}