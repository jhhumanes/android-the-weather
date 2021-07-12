package com.josehumaneshumanes.theweather.data.datasource

import com.josehumaneshumanes.theweather.data.common.Result
import com.josehumaneshumanes.theweather.domain.City

interface CityRemoteDataSource {

    suspend fun getAllCities(): Result<List<City>>

}
