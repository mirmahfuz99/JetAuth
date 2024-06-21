package com.jetauth.core.di

import com.jetauth.core.api.JetAuthApi
import com.jetauth.core.db.JetAuthDatabase
import com.jetauth.features.login.data.repository.LoginRepoImpl
import com.jetauth.features.login.data.repository.LoginRepository
import com.jetauth.features.signup.data.repository.SignUpRepository
import com.jetauth.features.signup.data.repository.SignUpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        jetAuthApi: JetAuthApi,
        jetAuthDatabase: JetAuthDatabase
    ): LoginRepository {
        return LoginRepoImpl(jetAuthApi,jetAuthDatabase)
    }


    @Provides
    @Singleton
    fun provideSignUpRepository(
        jetAuthApi: JetAuthApi,
    ): SignUpRepository {
        return SignUpRepositoryImpl(jetAuthApi)
    }
}