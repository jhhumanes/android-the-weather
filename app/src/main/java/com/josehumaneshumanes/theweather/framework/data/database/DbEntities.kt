package com.josehumaneshumanes.theweather.framework.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String
)
