package com.josehumaneshumanes.theweather.data.repository

import com.josehumaneshumanes.theweather.data.common.ErrorEntity
import com.josehumaneshumanes.theweather.data.common.Result
import com.josehumaneshumanes.theweather.data.common.Result.Error
import com.josehumaneshumanes.theweather.data.common.Result.Success
import com.josehumaneshumanes.theweather.data.datasource.CityLocalDataSource
import com.josehumaneshumanes.theweather.data.datasource.CityRemoteDataSource
import com.josehumaneshumanes.theweather.data.datasource.ControlDataSource
import com.josehumaneshumanes.theweather.domain.City

class CityRepository(
    private val cityLocalDataSource: CityLocalDataSource,
    private val cityRemoteDataSource: CityRemoteDataSource,
    private val controlDataSource: ControlDataSource
) {

    suspend fun preloadCities(): Result<Unit> {
        val result = loadCities()
        return if (result == null) {
            Success(Unit)
        } else {
            Error(result)
        }
    }

    suspend fun getAllCities(): Result<List<City>> {
        val result = loadCities()
        return if (result == null) {
            cityLocalDataSource.getAllCities()
        } else {
            Error(result)
        }
    }

    private suspend fun loadCities(): ErrorEntity? {
        val noLocalCities = cityLocalDataSource.isEmpty().toSuccessOrNull() ?: false
        val shouldRefresh = controlDataSource.shouldRefreshCities().toSuccessOrNull() ?: false

        if (noLocalCities || shouldRefresh) {
            when (val remoteCities = cityRemoteDataSource.getAllCities()) {
                is Success -> {
                    cityLocalDataSource.removeAllCities()
                    cityLocalDataSource.saveCities(remoteCities.data)
                    controlDataSource.saveCitiesReloadedTimestamp()
                }
                is Error -> return remoteCities.error
            }
        }
        return null
    }

}
