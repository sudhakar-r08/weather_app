package com.sudhakar.app.weatherapp.ui.search

import com.sudhakar.app.weatherapp.core.BaseViewState
import com.sudhakar.app.weatherapp.db.entity.CitiesForSearchEntity
import com.sudhakar.app.weatherapp.utils.domain.Status


class SearchViewState(
    val status: Status,
    val error: String? = null,
    val data: List<CitiesForSearchEntity>? = null
) : BaseViewState(status, error) {
    fun getSearchResult() = data
}
