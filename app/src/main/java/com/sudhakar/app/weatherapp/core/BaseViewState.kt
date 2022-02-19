package com.sudhakar.app.weatherapp.core

import com.sudhakar.app.weatherapp.utils.domain.Status


open class BaseViewState(val baseStatus: Status, val baseError: String?) {
    fun isLoading() = baseStatus == Status.LOADING
    fun getErrorMessage() = baseError
    fun shouldShowErrorMessage() = baseError != null
}
