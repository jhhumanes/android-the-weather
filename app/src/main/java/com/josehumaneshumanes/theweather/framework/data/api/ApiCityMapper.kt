package com.josehumaneshumanes.theweather.framework.data.api

import com.josehumaneshumanes.theweather.data.common.Mapper
import com.josehumaneshumanes.theweather.domain.City

class ApiCityMapper : Mapper<AemetCity, City> {

    override fun map(input: AemetCity): City = City(input.id.substring(2).toInt(), input.nombre)

}
