package com.sudhakar.app.weatherapp.ui.search

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.sudhakar.app.weatherapp.core.BaseViewModel
import com.sudhakar.app.weatherapp.core.Constants
import com.sudhakar.app.weatherapp.db.entity.CoordEntity
import com.sudhakar.app.weatherapp.domain.usecase.SearchCitiesUseCase
import com.sudhakar.app.weatherapp.repo.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject internal constructor(
    private val useCase: SearchCitiesUseCase,
    private val pref: UserPreferencesRepository
) : BaseViewModel() {

    private val _searchParams: MutableLiveData<SearchCitiesUseCase.SearchCitiesParams> = MutableLiveData()
    fun getSearchViewState() = searchViewState

    private val searchViewState: LiveData<SearchViewState> = _searchParams.switchMap { params ->
        useCase.execute(params)
    }

    fun setSearchParams(params: SearchCitiesUseCase.SearchCitiesParams) {
        if (_searchParams.value == params) {
            return
        }
        _searchParams.postValue(params)
    }

    fun saveCoordsToSharedPref(coordEntity: CoordEntity): Single<String> {
        return Single.create<String> {
            pref.putString(Constants.Coords.LAT, coordEntity.lat.toString())
            pref.putString(Constants.Coords.LON, coordEntity.lon.toString())
            it.onSuccess("")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}
