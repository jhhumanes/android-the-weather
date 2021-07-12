package com.josehumaneshumanes.theweather.framework.data.database

import com.josehumaneshumanes.theweather.data.common.Mapper
import com.josehumaneshumanes.theweather.domain.City as DomainCity
import com.josehumaneshumanes.theweather.framework.data.database.City as DbCity

class DomainCityMapper : Mapper<DomainCity, DbCity> {

    override fun map(input: DomainCity): DbCity = DbCity(input.id, input.name)

}