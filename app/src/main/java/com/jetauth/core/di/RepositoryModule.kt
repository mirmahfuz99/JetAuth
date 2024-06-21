package com.jetauth.core.di

import com.jetauth.core.api.JetAuthApi
import com.jetauth.features.login.data.repository.LoginRepoImpl
import com.jetauth.features.login.data.repository.LoginRepository
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
    fun provideLoginRepository(jetAuthApi: JetAuthApi): LoginRepository {
        return LoginRepoImpl(jetAuthApi)
    }
}