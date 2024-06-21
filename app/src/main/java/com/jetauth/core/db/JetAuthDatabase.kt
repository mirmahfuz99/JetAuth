package com.jetauth.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jetauth.features.login.data.datasource.local.UserPreferencesDao
import com.jetauth.features.login.data.model.UserPreferences

@Database(
    entities = [
        UserPreferences::class
    ],
    version = 1,
    exportSchema = false
)
abstract class JetAuthDatabase: RoomDatabase() {

    abstract fun userPreferencesDao(): UserPreferencesDao

    companion object{
        const val DATABASE_NAME = "jet_auth_db"
    }
}