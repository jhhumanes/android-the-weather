package com.josehumaneshumanes.theweather.usecases

import com.josehumaneshumanes.theweather.data.common.Result
import com.josehumaneshumanes.theweather.data.repository.CityRepository
import com.josehumaneshumanes.theweather.domain.City

class GetCities(private val cityRepository: CityRepository) {

    suspend fun invoke(): Result<List<City>> = cityRepository.getAllCities()

}
