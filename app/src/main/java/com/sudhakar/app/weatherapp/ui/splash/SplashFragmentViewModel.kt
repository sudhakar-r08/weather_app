package com.sudhakar.app.weatherapp.ui.splash

import android.content.SharedPreferences
import com.sudhakar.app.weatherapp.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    var sharedPreferences: SharedPreferences
) : BaseViewModel() {
    var navigateDashboard = false
}
