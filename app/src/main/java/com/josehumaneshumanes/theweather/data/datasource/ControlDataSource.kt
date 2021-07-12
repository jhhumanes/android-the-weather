package com.josehumaneshumanes.theweather.data.datasource

import com.josehumaneshumanes.theweather.data.common.Result

interface ControlDataSource {

    suspend fun shouldRefreshCities(): Result<Boolean>

    suspend fun saveCitiesReloadedTimestamp(): Result<Unit>

}
