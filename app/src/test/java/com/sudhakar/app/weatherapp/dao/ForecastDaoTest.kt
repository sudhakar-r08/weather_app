package com.sudhakar.app.weatherapp.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.sudhakar.app.weatherapp.db.WeatherDatabase
import com.sudhakar.app.weatherapp.db.dao.ForecastDao
import com.sudhakar.app.weatherapp.util.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ForecastDaoTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var forecastDao: ForecastDao

    @Before
    fun setUp() {
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        forecastDao = weatherDatabase.forecastDao()
    }

    @After
    fun closeDatabase() {
        weatherDatabase.close()
    }

    @Test
    fun `insert a forecast to forecast dao`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(3, cityName_2))

        // Then
        val value = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(value.city?.cityCountry).isEqualTo("Turkey")
        Truth.assertThat(value.city?.cityName).isEqualTo(cityName_2)
    }

    @Test
    fun `insert two forecast to forecast dao and then delete all after this operations count must be 0`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(3, cityName_2))
        forecastDao.insertForecast(createSampleForecastResponse(4, cityName_1))

        val value = forecastDao.getCount()
        Truth.assertThat(value).isEqualTo(2)

        // Then
        forecastDao.deleteAll()
        val newValue = forecastDao.getCount()
        Truth.assertThat(newValue).isEqualTo(0)
    }

    @Test
    fun `insert a forecast and then update`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(1, cityName_2))
        val value = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(value.city?.cityName).isEqualTo(cityName_2)

        // Then
        forecastDao.updateForecast(createSampleForecastResponse(1, cityName_1))
        val updatedValue = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(updatedValue.city?.cityName).isEqualTo(cityName_1)
    }

    @Test
    fun `delete and insert a forecast`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(1, cityName_2))
        val count = forecastDao.getCount()
        Truth.assertThat(count).isEqualTo(1)

        // Then
        forecastDao.deleteAndInsert(createSampleForecastResponse(2, cityName_3))
        val newCount = forecastDao.getCount()
        val value = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(newCount).isEqualTo(1)
        Truth.assertThat(value.city?.cityName).isEqualTo(cityName_3)
    }

    @Test
    fun `first insert a forecast then delete and count must be zero`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(1, cityName_4))
        val count = forecastDao.getCount()
        Truth.assertThat(count).isEqualTo(1)

        // Then
        forecastDao.deleteForecast(createSampleForecastResponse(1, cityName_4))
        val newCount = forecastDao.getCount()
        Truth.assertThat(newCount).isEqualTo(0)
    }

    @Test
    fun `first insert a forecast and then get it with coords`() {
        // When
        forecastDao.insertForecast(
            createSampleForecastWithCoord(
                1,
                cityName_3,
                coordLat_3,
                coordLon_3
            )
        )
        forecastDao.insertForecast(
            createSampleForecastWithCoord(
                3,
                cityName_4,
                coordLat_4,
                coordLon_4
            )
        )
        val count = forecastDao.getCount()
        Truth.assertThat(count).isEqualTo(2)

        // Then
        val value = forecastDao.getForecastByCoord(coordLat_4, coordLon_4).getOrAwaitValue()
        Truth.assertThat(value.city?.cityName).isEqualTo(cityName_4)
    }
}
