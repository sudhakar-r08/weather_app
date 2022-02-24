package com.sudhakar.app.weatherapp.util

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.sudhakar.app.weatherapp.db.entity.*
import com.sudhakar.app.weatherapp.domain.model.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException(timeoutExceptionMessage)
        }
    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

// Data Generators
fun createSampleForecastResponse(id: Int, cityName: String): ForecastEntity {
    val weatherItem =
        WeatherItem(weatherItemIcon, weatherItemDescription, weatherItemMain, weatherItemId)
    val weather = listOf(weatherItem)
    val listItem = ListItem(
        123123, Rain(rain3h), "132121", Snow(snow3h), weather,
        Main(
            weatherMainTemp,
            weatherMainTempMin,
            weatherMainGrndLevel,
            weatherMainTempKf,
            weatherMainHumidity,
            weatherMainPressure,
            weatherMainSeaLevel,
            weatherMainTempMax
        ),
        Clouds(1), Sys("a"), Wind(windDeg, windSpeed)
    )
    val list = listOf(listItem)
    return ForecastEntity(
        id,
        CityEntity(country, CoordEntity(coordLon_2, coordLat_2), cityName, cityId),
        list
    )
}

fun createSampleForecastWithCoord(
    id: Int,
    cityName: String,
    lat: Double,
    lon: Double
): ForecastEntity {
    val list = emptyList<ListItem>()
    return ForecastEntity(id, CityEntity(country, CoordEntity(lon, lat), cityName, cityId), list)
}

fun generateCitiesForSearchEntity(id: String, name: String): CitiesForSearchEntity {
    return CitiesForSearchEntity(
        "Clear",
        country,
        CoordEntity(coordLon_2, coordLat_2),
        name,
        county,
        1,
        id
    )
}

fun generateCurrentWeatherEntity(name: String, id: Int): CurrentWeatherEntity {
    val weatherItem =
        WeatherItem(weatherItemIcon, weatherItemDescription, weatherItemMain, weatherItemId)
    val weather = listOf(weatherItem)
    return CurrentWeatherEntity(
        1,
        2,
        MainEntity(
            weatherMainTemp,
            weatherMainTempMin,
            weatherMainGrndLevel,
            weatherMainTempKf,
            weatherMainHumidity,
            weatherMainPressure,
            weatherMainSeaLevel,
            weatherMainTempMax
        ),
        null,
        3421399123,
        weather,
        name,
        id,
        tempScale,
        null
    )
}

fun createSampleForecastResponse(): ForecastResponse {
    val weatherItem =
        WeatherItem(weatherItemIcon, weatherItemDescription, weatherItemMain, weatherItemId)
    val weather = listOf(weatherItem)
    val listItem = ListItem(
        123123, Rain(rain3h), "132121", Snow(snow3h), weather,
        Main(
            weatherMainTemp,
            weatherMainTempMin,
            weatherMainGrndLevel,
            weatherMainTempKf,
            weatherMainHumidity,
            weatherMainPressure,
            weatherMainSeaLevel,
            weatherMainTempMax
        ),
        Clouds(1), Sys("a"), Wind(windDeg, windSpeed)
    )
    val list = listOf(listItem)
    return ForecastResponse(
        City(country, Coord(coordLon, coordLat), city, 10),
        null,
        null,
        null,
        list
    )
}

fun createSampleCurrentWeatherResponse(): CurrentWeatherResponse {
    val weatherItem =
        WeatherItem(weatherItemIcon, weatherItemDescription, weatherItemMain, weatherItemId)
    val weather = listOf(weatherItem)
    return CurrentWeatherResponse(
        null,
        null,
        Main(
            weatherMainTemp,
            weatherMainTempMin,
            weatherMainGrndLevel,
            weatherMainTempKf,
            weatherMainHumidity,
            weatherMainPressure,
            weatherMainSeaLevel,
            weatherMainTempMax
        ),
        Clouds(
            1
        ),
        Sys("a"),
        null,
        Coord(coordLon, coordLat),
        weather,
        city,
        null,
        10,
        null,
        null
    )
}

fun generateSampleSearchCitiesResponse(): SearchResponse {
    return SearchResponse(
        listOf(
            HitsItem(
                country,
                null,
                isCity = true,
                isCountry = false,
                administrative = listOf(
                    city
                ),
                adminLevel = null,
                postcode = null,
                county = listOf(county),
                geoloc = null,
                importance = null,
                objectID = "10",
                isSuburb = null,
                localeNames = null
            )
        )
    )
}
