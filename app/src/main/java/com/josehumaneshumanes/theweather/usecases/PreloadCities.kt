package com.josehumaneshumanes.theweather.usecases

import com.josehumaneshumanes.theweather.data.common.Result
import com.josehumaneshumanes.theweather.data.repository.CityRepository

class PreloadCities(private val cityRepository: CityRepository) {

    suspend fun invoke(): Result<Unit> = cityRepository.preloadCities()

}
