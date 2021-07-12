package com.josehumaneshumanes.theweather.framework.data.datasource

import android.content.Context
import com.josehumaneshumanes.theweather.BuildConfig
import com.josehumaneshumanes.theweather.data.common.Result
import com.josehumaneshumanes.theweather.data.common.Result.Success
import com.josehumaneshumanes.theweather.data.datasource.ControlDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val PREFS_FILENAME = "TheWeatherPrefs"
private const val PREFS_CITIES_LAST_DATA_RELOAD = "CITIES_LAST_DATA_RELOAD"

class PrefsControlDataSource(context: Context) : ControlDataSource {

    private val storage = context.getSharedPreferences(PREFS_FILENAME, 0)

    override suspend fun shouldRefreshCities(): Result<Boolean> {
        val now: Long = System.currentTimeMillis()

        val result = withContext<Boolean>(Dispatchers.IO) {
            if (storage.contains(PREFS_CITIES_LAST_DATA_RELOAD)) {
                val expiresInMillis: Long =
                    BuildConfig.CITIES_RELOAD_EXPIRES_MINUTES.toLong() * 60 * 1000
                val lastTimestamp: Long =
                    storage.getLong(PREFS_CITIES_LAST_DATA_RELOAD, 0) + expiresInMillis

                now > lastTimestamp
            } else {
                true
            }
        }

        return Success(result)
    }

    override suspend fun saveCitiesReloadedTimestamp(): Result<Unit> {
        withContext(Dispatchers.IO) {
            storage.edit().putLong(PREFS_CITIES_LAST_DATA_RELOAD, System.currentTimeMillis())
                .apply()
        }
        return Success(Unit)
    }

}
