package com.josehumaneshumanes.theweather.framework.data.datasource

import com.josehumaneshumanes.theweather.data.common.ErrorEntity
import com.josehumaneshumanes.theweather.data.common.ListMapperImpl
import com.josehumaneshumanes.theweather.data.common.Result
import com.josehumaneshumanes.theweather.data.datasource.CityRemoteDataSource
import com.josehumaneshumanes.theweather.domain.City
import com.josehumaneshumanes.theweather.framework.data.api.Aemet
import com.josehumaneshumanes.theweather.framework.data.api.ApiCityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitCityRemoteDataSource(private val apikey: String, private val aemet: Aemet) :
    CityRemoteDataSource {

    override suspend fun getAllCities(): Result<List<City>> {
        val mapper = ListMapperImpl(ApiCityMapper())

        return try {
            val cities = withContext(Dispatchers.IO) { aemet.service.getAllCities(apikey) }
            Result.Success(mapper.map(cities))
        } catch (thr: Throwable) {
            Result.Error(ErrorEntity.ApiError.Network)
        }
    }

}
