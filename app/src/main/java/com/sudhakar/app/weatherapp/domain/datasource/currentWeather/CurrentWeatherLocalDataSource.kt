package com.sudhakar.app.weatherapp.domain.datasource.currentWeather

import com.sudhakar.app.weatherapp.db.dao.CurrentWeatherDao
import com.sudhakar.app.weatherapp.db.entity.CurrentWeatherEntity
import com.sudhakar.app.weatherapp.domain.model.CurrentWeatherResponse
import javax.inject.Inject


class CurrentWeatherLocalDataSource @Inject constructor(
    private val currentWeatherDao: CurrentWeatherDao
) {

    fun getCurrentWeather() = currentWeatherDao.getCurrentWeather()

    fun insertCurrentWeather(currentWeather: CurrentWeatherResponse) =
        currentWeatherDao.deleteAndInsert(
            CurrentWeatherEntity(currentWeather)
        )
}
