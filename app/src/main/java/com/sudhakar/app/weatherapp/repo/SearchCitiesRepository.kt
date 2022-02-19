package com.sudhakar.app.weatherapp.repo

import androidx.lifecycle.LiveData
import com.sudhakar.app.weatherapp.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.sudhakar.app.weatherapp.db.entity.CitiesForSearchEntity
import com.sudhakar.app.weatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.sudhakar.app.weatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import com.sudhakar.app.weatherapp.domain.model.SearchResponse
import com.sudhakar.app.weatherapp.utils.domain.RateLimiter
import com.sudhakar.app.weatherapp.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject



class SearchCitiesRepository @Inject constructor(
    private val searchCitiesRemoteDataSource: SearchCitiesRemoteDataSource,
    private val searchCitiesLocalDataSource: SearchCitiesLocalDataSource,
) {

    private val rateLimiter = RateLimiter<String>(1, TimeUnit.SECONDS)

    fun loadCitiesByCityName(cityName: String?): LiveData<Resource<List<CitiesForSearchEntity>>> {
        return object : NetworkBoundResource<List<CitiesForSearchEntity>, SearchResponse>() {
            override fun saveCallResult(item: SearchResponse) = searchCitiesLocalDataSource.insertCities(
                item
            )

            override fun shouldFetch(data: List<CitiesForSearchEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<CitiesForSearchEntity>> = searchCitiesLocalDataSource.getCityByName(
                cityName
            )

            override fun createCall(): Single<SearchResponse> = searchCitiesRemoteDataSource.getCityWithQuery(
                cityName
                    ?: ""
            )

            override fun onFetchFailed() = rateLimiter.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
