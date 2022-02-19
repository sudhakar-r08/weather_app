package com.sudhakar.app.weatherapp.ui.dashboard

import com.sudhakar.app.weatherapp.core.BaseViewState
import com.sudhakar.app.weatherapp.db.entity.CurrentWeatherEntity
import com.sudhakar.app.weatherapp.utils.domain.Status


class CurrentWeatherViewState(
    val status: Status,
    val error: String? = null,
    val data: CurrentWeatherEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
