package com.sudhakar.app.weatherapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.sudhakar.app.weatherapp.core.Constants
import com.sudhakar.app.weatherapp.db.entity.CurrentWeatherEntity
import com.sudhakar.app.weatherapp.repo.CurrentWeatherRepository
import com.sudhakar.app.weatherapp.ui.dashboard.CurrentWeatherViewState
import com.sudhakar.app.weatherapp.utils.UseCaseLiveData
import com.sudhakar.app.weatherapp.utils.domain.Resource
import javax.inject.Inject


class CurrentWeatherUseCase @Inject internal constructor(
    private val repository: CurrentWeatherRepository
) : UseCaseLiveData<CurrentWeatherViewState, CurrentWeatherUseCase.CurrentWeatherParams, CurrentWeatherRepository>() {

    override fun getRepository(): CurrentWeatherRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: CurrentWeatherParams?): LiveData<CurrentWeatherViewState> {
        return repository.loadCurrentWeatherByGeoCords(
            params?.lat?.toDouble() ?: 0.0,
            params?.lon?.toDouble() ?: 0.0,
            params?.fetchRequired
                ?: false,
            units = params?.units ?: Constants.Coords.METRIC
        ).map {
            onCurrentWeatherResultReady(it)
        }
    }

    private fun onCurrentWeatherResultReady(resource: Resource<CurrentWeatherEntity>): CurrentWeatherViewState {
        return CurrentWeatherViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class CurrentWeatherParams(
        val lat: String = "",
        val lon: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
