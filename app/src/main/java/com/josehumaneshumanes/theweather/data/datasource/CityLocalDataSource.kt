package com.josehumaneshumanes.theweather.data.datasource

import com.josehumaneshumanes.theweather.data.common.Result
import com.josehumaneshumanes.theweather.domain.City

interface CityLocalDataSource {

    suspend fun isEmpty(): Result<Boolean>

    suspend fun getAllCities(): Result<List<City>>

    suspend fun saveCities(cities: List<City>): Result<Unit>

    suspend fun removeAllCities(): Result<Unit>

}
