package com.sudhakar.app.weatherapp.ui.dashboard

import android.transition.TransitionInflater
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.sudhakar.app.weatherapp.R
import com.sudhakar.app.weatherapp.core.BaseFragment
import com.sudhakar.app.weatherapp.core.Constants
import com.sudhakar.app.weatherapp.databinding.FragmentDashboardBinding
import com.sudhakar.app.weatherapp.domain.model.ListItem
import com.sudhakar.app.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.sudhakar.app.weatherapp.domain.usecase.ForecastUseCase
import com.sudhakar.app.weatherapp.ui.dashboard.forecast.ForecastAdapter
import com.sudhakar.app.weatherapp.ui.main.MainActivity
import com.sudhakar.app.weatherapp.utils.extensions.isNetworkAvailable
import com.sudhakar.app.weatherapp.utils.extensions.observeWith
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(
    R.layout.fragment_dashboard,
    DashboardFragmentViewModel::class.java,
) {

    override fun init() {
        super.init()
        initForecastAdapter()
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(
            android.R.transition.move
        )

        val lat: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LAT)
        val lon: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON)

        if (lat?.isNotEmpty() == true && lon?.isNotEmpty() == true) {
            binding.viewModel?.setCurrentWeatherParams(
                CurrentWeatherUseCase.CurrentWeatherParams(
                    lat,
                    lon,
                    isNetworkAvailable(requireContext()),
                    Constants.Coords.METRIC
                )
            )
            binding.viewModel?.setForecastParams(
                ForecastUseCase.ForecastParams(
                    lat,
                    lon,
                    isNetworkAvailable(requireContext()),
                    Constants.Coords.METRIC
                )
            )
        }

        binding.viewModel?.getForecastViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            with(binding) {
                viewState = it
                it.data?.list?.let { forecasts -> initForecast(forecasts) }
                (activity as MainActivity).viewModel.toolbarTitle.set(
                    it.data?.city?.getCityAndCountry()
                )
            }
        }

        binding.viewModel?.getCurrentWeatherViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            with(binding) {
                containerForecast.viewState = it
            }
        }
    }

    private fun initForecastAdapter() {
        val adapter =
            ForecastAdapter { item, cardView, forecastIcon, dayOfWeek, tempMaxMin ->
                val action =
                    DashboardFragmentDirections.actionDashboardFragmentToWeatherDetailFragment(
                        item
                    )
                findNavController()
                    .navigate(
                        action,
                        FragmentNavigator.Extras.Builder()
                            .addSharedElements(
                                mapOf(
                                    cardView to cardView.transitionName,
                                    forecastIcon to forecastIcon.transitionName,
                                    dayOfWeek to dayOfWeek.transitionName,
                                    tempMaxMin to tempMaxMin.transitionName
                                )
                            )
                            .build()
                    )
            }

        binding.recyclerForecast.adapter = adapter
        binding.recyclerForecast.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        postponeEnterTransition()
        binding.recyclerForecast.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun initForecast(list: List<ListItem>) {
        (binding.recyclerForecast.adapter as ForecastAdapter).submitList(list)
    }
}
