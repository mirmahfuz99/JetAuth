package com.jetauth.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.jetauth.core.db.JetAuthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesJetAuthDatabase(application: Application): JetAuthDatabase {
        return Room.databaseBuilder(
            application,
            JetAuthDatabase::class.java,
            JetAuthDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    fun provideContext(
        @ApplicationContext context: Context,
    ): Context {
        return context
    }

}