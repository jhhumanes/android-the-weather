package com.josehumaneshumanes.theweather.framework.di

import android.content.Context
import androidx.room.Room
import com.josehumaneshumanes.theweather.BuildConfig
import com.josehumaneshumanes.theweather.data.datasource.CityLocalDataSource
import com.josehumaneshumanes.theweather.data.datasource.CityRemoteDataSource
import com.josehumaneshumanes.theweather.data.datasource.ControlDataSource
import com.josehumaneshumanes.theweather.data.repository.CityRepository
import com.josehumaneshumanes.theweather.framework.data.api.Aemet
import com.josehumaneshumanes.theweather.framework.data.database.AemetDatabase
import com.josehumaneshumanes.theweather.framework.data.datasource.PrefsControlDataSource
import com.josehumaneshumanes.theweather.framework.data.datasource.RetrofitCityRemoteDataSource
import com.josehumaneshumanes.theweather.framework.data.datasource.RoomCityLocalDatasource
import com.josehumaneshumanes.theweather.usecases.GetCities
import com.josehumaneshumanes.theweather.usecases.PreloadCities
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppHiltModule {

    @Provides
    @Singleton
    @Named("APIKEY")
    fun apiKeyProvider(): String = BuildConfig.AEMET_APIKEY

    @Provides
    @Singleton
    fun dataBaseProvider(@ApplicationContext appContext: Context): AemetDatabase =
        Room.databaseBuilder(appContext, AemetDatabase::class.java, AemetDatabase.databaseName)
            .build()

    @Provides
    @Singleton
    fun aemetProvider() = Aemet(BuildConfig.AEMET_APIURL)

    @Provides
    @Singleton
    fun cityLocalDataSourceProvider(
        database: AemetDatabase
    ): CityLocalDataSource = RoomCityLocalDatasource(database)

    @Provides
    @Singleton
    fun cityRemoteDataSourceProvider(
        @Named("APIKEY") apikey: String,
        aemet: Aemet
    ): CityRemoteDataSource = RetrofitCityRemoteDataSource(apikey, aemet)

    @Provides
    @Singleton
    fun controlDataSourceProvider(@ApplicationContext appContext: Context): ControlDataSource =
        PrefsControlDataSource(appContext)

    @Provides
    @Singleton
    fun cityRepositoryProvider(
        cityLocalDataSource: CityLocalDataSource,
        cityRemoteDataSource: CityRemoteDataSource,
        controlDataSource: ControlDataSource
    ): CityRepository = CityRepository(cityLocalDataSource, cityRemoteDataSource, controlDataSource)

    @Provides
    @Singleton
    fun preloadCitiesProvider(cityRepository: CityRepository): PreloadCities =
        PreloadCities(cityRepository)

    @Provides
    @Singleton
    fun getCitiesProvider(cityRepository: CityRepository): GetCities = GetCities(cityRepository)

}
