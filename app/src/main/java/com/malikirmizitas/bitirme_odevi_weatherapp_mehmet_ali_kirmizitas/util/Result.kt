package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util

sealed class Result<T>(val data :  T?) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(errorMessage: String) : Result<T>(null)
}