package com.josehumaneshumanes.theweather.data.common

sealed class ErrorEntity {

    object Unknown : ErrorEntity()

    sealed class ApiError : ErrorEntity() {
        object Network : ErrorEntity()

        object NotFound : ErrorEntity()

        object AccessDenied : ErrorEntity()

        object ServiceUnavailable : ErrorEntity()
    }

    sealed class FileError : ErrorEntity() {

        object NotFound : FileError()

        object ReadError : FileError()
    }
}
