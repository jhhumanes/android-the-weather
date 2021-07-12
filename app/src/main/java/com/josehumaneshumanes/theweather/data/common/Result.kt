package com.josehumaneshumanes.theweather.data.common

sealed class Result<T> {

    data class Error<T>(val error: ErrorEntity) : Result<T>()

    data class Success<T>(val data: T) : Result<T>()

    fun <S> fold(errorFolder: (ErrorEntity) -> S, successFolder: (T) -> S): S {
        return when (this) {
            is Error -> errorFolder(error)
            is Success -> successFolder(data)
        }
    }

    fun <S> map(mapper: (T) -> S): Result<S> {
        return when (this) {
            is Error -> Error(error)
            is Success -> Success(mapper(data))
        }
    }

    fun toErrorOrNull(): ErrorEntity? = when (this) {
        is Error -> error
        is Success -> null
    }

    fun toSuccessOrNull(): T? = when (this) {
        is Error -> null
        is Success -> data
    }

}
