package com.sudhakar.app.weatherapp.viewModel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.sudhakar.app.weatherapp.db.WeatherDatabase
import com.sudhakar.app.weatherapp.db.dao.ForecastDao
import com.sudhakar.app.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.sudhakar.app.weatherapp.ui.weather_detail.WeatherDetailViewModel
import com.sudhakar.app.weatherapp.util.createSampleForecastResponse
import com.sudhakar.app.weatherapp.util.getOrAwaitValue
import io.mockk.MockKAnnotations
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class WeatherDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherDetailViewModel: WeatherDetailViewModel
    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var forecastDao: ForecastDao
    private lateinit var forecastLocalDataSource: ForecastLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        forecastDao = weatherDatabase.forecastDao()
        forecastLocalDataSource = ForecastLocalDataSource(forecastDao)
        weatherDetailViewModel = WeatherDetailViewModel(forecastLocalDataSource)
    }

    @After
    fun closeDatabase() {
        weatherDatabase.close()
    }

    @Test
    fun `insert forecast and when getForecast called the livedata result must be ForecastEntity`() {
        // When
        forecastLocalDataSource.insertForecast(createSampleForecastResponse())

        // Then
        val result = weatherDetailViewModel.getForecast().getOrAwaitValue()
        Truth.assertThat(result.city?.cityName).isEqualTo("Istanbul")
    }
}
