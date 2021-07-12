package com.josehumaneshumanes.theweather.framework.ui.splash

import com.josehumaneshumanes.theweather.data.common.Result
import com.josehumaneshumanes.theweather.framework.ui.common.ScopedViewModel
import com.josehumaneshumanes.theweather.usecases.PreloadCities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preloadCities: PreloadCities,
    uiDispatcher: CoroutineDispatcher
) :
    ScopedViewModel(uiDispatcher) {

    private val _navigate = MutableStateFlow<Boolean>(false)
    val navigate: StateFlow<Boolean>
        get() = _navigate

    private val _showError = MutableStateFlow<Boolean>(false)
    val showError: StateFlow<Boolean>
        get() = _showError

    fun onCreate() {
        launch {
            when (preloadCities.invoke()) {
                is Result.Error -> {
                    _showError.value = true
                }
                is Result.Success -> {
                    _navigate.value = true
                }
            }
        }
    }

}
