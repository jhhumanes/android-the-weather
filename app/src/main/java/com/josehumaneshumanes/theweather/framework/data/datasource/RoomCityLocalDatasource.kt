package com.josehumaneshumanes.theweather.framework.data.datasource

import com.josehumaneshumanes.theweather.data.common.ErrorEntity
import com.josehumaneshumanes.theweather.data.common.ListMapperImpl
import com.josehumaneshumanes.theweather.data.common.Result
import com.josehumaneshumanes.theweather.data.datasource.CityLocalDataSource
import com.josehumaneshumanes.theweather.domain.City
import com.josehumaneshumanes.theweather.framework.data.database.AemetDatabase
import com.josehumaneshumanes.theweather.framework.data.database.DbCityMapper
import com.josehumaneshumanes.theweather.framework.data.database.DomainCityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomCityLocalDatasource(private val database: AemetDatabase) : CityLocalDataSource {

    override suspend fun isEmpty(): Result<Boolean> {
        val count = withContext(Dispatchers.IO) { database.cityDao().getCount() }
        return Result.Success(count <= 0)
    }


    override suspend fun getAllCities(): Result<List<City>> {
        return try {
            val mapper = ListMapperImpl(DbCityMapper())
            val cities = withContext(Dispatchers.IO) { database.cityDao().getAll() }
            Result.Success(mapper.map(cities))
        } catch (thr: Throwable) {
            Result.Error(ErrorEntity.Unknown)
        }
    }

    override suspend fun saveCities(cities: List<City>): Result<Unit> {
        val mapper = ListMapperImpl(DomainCityMapper())
        withContext(Dispatchers.IO) { database.cityDao().insert(mapper.map(cities)) }
        return Result.Success(Unit)
    }

    override suspend fun removeAllCities(): Result<Unit> {
        withContext(Dispatchers.IO) { database.cityDao().removeAll() }
        return Result.Success(Unit)
    }

}
