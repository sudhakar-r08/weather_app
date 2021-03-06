package com.sudhakar.app.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.sudhakar.app.weatherapp.core.Constants.DB.DB_NAME
import com.sudhakar.app.weatherapp.db.WeatherDatabase
import com.sudhakar.app.weatherapp.db.dao.CitiesForSearchDao
import com.sudhakar.app.weatherapp.db.dao.CurrentWeatherDao
import com.sudhakar.app.weatherapp.db.dao.ForecastDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            DB_NAME
        ).build()

    @Provides
    @Singleton
    fun provideForecastDao(db: WeatherDatabase): ForecastDao = db.forecastDao()

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(db: WeatherDatabase): CurrentWeatherDao = db.currentWeatherDao()

    @Provides
    @Singleton
    fun provideCitiesForSearchDao(db: WeatherDatabase): CitiesForSearchDao = db.citiesForSearchDao()

}