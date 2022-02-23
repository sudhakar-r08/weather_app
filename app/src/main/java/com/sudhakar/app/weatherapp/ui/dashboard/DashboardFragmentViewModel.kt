package com.sudhakar.app.weatherapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.sudhakar.app.weatherapp.core.BaseViewModel
import com.sudhakar.app.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.sudhakar.app.weatherapp.domain.usecase.ForecastUseCase
import com.sudhakar.app.weatherapp.repo.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DashboardFragmentViewModel @Inject internal constructor(
    private val forecastUseCase: ForecastUseCase,
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    val sharedPreferences: UserPreferencesRepository
) : BaseViewModel() {

    private val _forecastParams: MutableLiveData<ForecastUseCase.ForecastParams> = MutableLiveData()
    private val _currentWeatherParams: MutableLiveData<CurrentWeatherUseCase.CurrentWeatherParams> =
        MutableLiveData()

    fun getForecastViewState() = forecastViewState
    fun getCurrentWeatherViewState() = currentWeatherViewState

    private val forecastViewState: LiveData<ForecastViewState> =
        _forecastParams.switchMap { params ->
            forecastUseCase.execute(params)
        }
    private val currentWeatherViewState: LiveData<CurrentWeatherViewState> =
        _currentWeatherParams.switchMap { params ->
            currentWeatherUseCase.execute(params)
        }

    fun setForecastParams(params: ForecastUseCase.ForecastParams) {
        if (_forecastParams.value == params) {
            return
        }
        _forecastParams.postValue(params)
    }

    fun setCurrentWeatherParams(params: CurrentWeatherUseCase.CurrentWeatherParams) {
        if (_currentWeatherParams.value == params) {
            return
        }
        _currentWeatherParams.postValue(params)
    }
}
