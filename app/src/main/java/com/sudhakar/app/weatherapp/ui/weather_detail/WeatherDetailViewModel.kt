package com.sudhakar.app.weatherapp.ui.weather_detail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sudhakar.app.weatherapp.core.BaseViewModel
import com.sudhakar.app.weatherapp.db.entity.ForecastEntity
import com.sudhakar.app.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.sudhakar.app.weatherapp.domain.model.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val forecastLocalDataSource: ForecastLocalDataSource
) : BaseViewModel() {

    var weatherItem: ObservableField<ListItem> = ObservableField()
    private var forecastLiveData: LiveData<ForecastEntity> = MutableLiveData()
    var selectedDayDate: String? = null
    var selectedDayForecastLiveData: MutableLiveData<List<ListItem>> = MutableLiveData()

    fun getForecastLiveData() = forecastLiveData

    fun getForecast(): LiveData<ForecastEntity> {
        return forecastLocalDataSource.getForecast()
    }
}
