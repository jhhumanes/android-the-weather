package com.josehumaneshumanes.theweather.framework.data.database

import com.josehumaneshumanes.theweather.data.common.Mapper
import com.josehumaneshumanes.theweather.domain.City as DomainCity
import com.josehumaneshumanes.theweather.framework.data.database.City as DbCity

class DbCityMapper : Mapper<DbCity, DomainCity> {

    override fun map(input: DbCity): DomainCity = DomainCity(input.id, input.name)

}
