package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.Result
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.SearchAutoCompleteItem
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    suspend fun getCurrentCityWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("aqi") airQuality: String
    ): Result<WeatherResponse>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("aqi") airQuality: String,
        @Query("alerts") alert: String
    ): WeatherResponse?

    @GET("search.json")
    suspend fun getSearchAutoComplete(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ): List<SearchAutoCompleteItem>
}