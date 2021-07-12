package com.josehumaneshumanes.theweather.framework.data.api

import retrofit2.http.GET
import retrofit2.http.Header

interface AemetService {

    @GET("maestro/municipios")
    suspend fun getAllCities(@Header("api_key") apiKey: String): List<AemetCity>

}
