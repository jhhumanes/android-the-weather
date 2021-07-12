package com.josehumaneshumanes.theweather.framework.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        City::class
    ],
    exportSchema = false
)
abstract class AemetDatabase: RoomDatabase() {

    companion object {
        const val databaseName = "theweather-db"
    }

    abstract fun cityDao(): CityDao

}
