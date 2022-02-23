package com.sudhakar.app.weatherapp.ui.splash

import com.sudhakar.app.weatherapp.core.BaseViewModel
import com.sudhakar.app.weatherapp.repo.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    var sharedPreferences: UserPreferencesRepository
) : BaseViewModel() {
    var navigateDashboard = false
}
