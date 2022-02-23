package com.sudhakar.app.weatherapp.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.sudhakar.app.weatherapp.repo.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        UserPreferencesRepository.getInstance(context)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

}