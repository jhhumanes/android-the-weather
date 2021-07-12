package com.josehumaneshumanes.theweather.framework.data.database

import androidx.room.*

@Dao
interface CityDao {

    @Transaction
    @Query("SELECT COUNT(id) FROM City")
    fun getCount(): Int

    @Transaction
    @Query("SELECT * FROM City")
    fun getAll(): List<City>

    @Transaction
    @Query("DELETE FROM City")
    fun removeAll(): Int

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cities: List<City>)

}
