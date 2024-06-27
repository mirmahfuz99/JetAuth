package com.jetauth.core.di

import android.util.Log
import com.jetauth.core.api.Constants
import com.jetauth.core.api.JetAuthApi
import com.jetauth.core.db.JetAuthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(
        jetAuthDatabase: JetAuthDatabase
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    val language = Constants.ENGLISH_LOCALE_LANG
                    builder.header(
                        "Accept-Language", language,
                    )
                    builder.addHeader(
                        "Content-Type", "application/x-www-form-urlencoded",
                    )
                    val token = runBlocking {
                         jetAuthDatabase.userPreferencesDao().getUserPreferences()?.token.toString()
                    }
                    Log.d("token", token)
                    if(token != "null"){
                        builder.addHeader(
                            "Authorization", "Bearer $token",
                        )
                    }

                    return@Interceptor chain.proceed(builder.build())
                }
            )
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providesIntegrateApi(retrofit: Retrofit): JetAuthApi =
        retrofit.create(JetAuthApi::class.java)
}