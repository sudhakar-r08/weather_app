package com.sudhakar.app.weatherapp

import com.sudhakar.app.weatherapp.dao.CitiesForSearchDaoTest
import com.sudhakar.app.weatherapp.dao.CurrentWeatherDaoTest
import com.sudhakar.app.weatherapp.dao.ForecastDaoTest
import com.sudhakar.app.weatherapp.repo.CurrentWeatherRepositoryTest
import com.sudhakar.app.weatherapp.repo.ForecastRepositoryTest
import com.sudhakar.app.weatherapp.viewModel.DashboardViewModelTest
import com.sudhakar.app.weatherapp.viewModel.SearchViewModelTest
import com.sudhakar.app.weatherapp.viewModel.WeatherDetailViewModelTest
import com.sudhakar.app.weatherapp.viewState.CurrentWeatherViewStateTest
import com.sudhakar.app.weatherapp.viewState.ForecastViewStateTest
import com.sudhakar.app.weatherapp.viewState.SearchViewStateTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CitiesForSearchDaoTest::class,
    CurrentWeatherDaoTest::class,
    CurrentWeatherViewStateTest::class,
    DashboardViewModelTest::class,
    ForecastDaoTest::class,
    ForecastViewStateTest::class,
    SearchViewStateTest::class,
    SearchViewModelTest::class,
    WeatherDetailViewModelTest::class,
    ForecastRepositoryTest::class,
    CurrentWeatherRepositoryTest::class
)
class TestSuite
