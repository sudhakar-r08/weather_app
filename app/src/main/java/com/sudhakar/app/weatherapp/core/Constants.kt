package com.sudhakar.app.weatherapp.core



object Constants {

    object NetworkService {
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val API_KEY_VALUE = "1487dd8a93bfd85d278d9ac8dcfee94c"
        const val RATE_LIMITER_TYPE = "data"
        const val API_KEY_QUERY = "appid"
    }

    object AlgoliaKeys {
        const val APPLICATION_ID = "plNW8IW0YOIN"
        const val SEARCH_API_KEY = "029766644cb160efa51f2a32284310eb"
    }

    object Coords {
        const val LAT = "lat"
        const val LON = "lon"
        const val METRIC = "metric"
    }
}
