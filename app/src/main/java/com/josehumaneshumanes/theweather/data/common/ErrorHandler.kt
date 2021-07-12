package com.josehumaneshumanes.theweather.data.common

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}
