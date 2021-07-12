package com.josehumaneshumanes.theweather.framework.di

import com.josehumaneshumanes.theweather.data.datasource.CityLocalDataSource
import com.josehumaneshumanes.theweather.data.datasource.CityRemoteDataSource
import com.josehumaneshumanes.theweather.data.repository.CityRepository
import com.josehumaneshumanes.theweather.framework.data.datasource.RetrofitCityRemoteDataSource
import com.josehumaneshumanes.theweather.framework.data.datasource.RoomCityLocalDatasource
import com.josehumaneshumanes.theweather.usecases.PreloadCities
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class SplashActivityHiltModule {

    @Provides
    fun uiDispatcherProvider(): CoroutineDispatcher = Dispatchers.Main

}
