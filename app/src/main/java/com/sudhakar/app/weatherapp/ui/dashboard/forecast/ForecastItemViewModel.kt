package com.sudhakar.app.weatherapp.ui.dashboard.forecast

import androidx.databinding.ObservableField
import com.sudhakar.app.weatherapp.core.BaseViewModel
import com.sudhakar.app.weatherapp.domain.model.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ForecastItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
