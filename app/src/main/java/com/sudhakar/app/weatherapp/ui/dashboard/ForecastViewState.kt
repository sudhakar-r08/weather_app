package com.sudhakar.app.weatherapp.ui.dashboard

import com.sudhakar.app.weatherapp.core.BaseViewState
import com.sudhakar.app.weatherapp.db.entity.ForecastEntity
import com.sudhakar.app.weatherapp.utils.domain.Status


class ForecastViewState(
    val status: Status,
    val error: String? = null,
    val data: ForecastEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
