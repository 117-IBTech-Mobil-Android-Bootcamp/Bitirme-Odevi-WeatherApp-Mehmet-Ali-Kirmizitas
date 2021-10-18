package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.SearchAutoCompleteItem
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    suspend fun getCurrentCityWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("aqi") airQuality: String
    ): WeatherResponse?

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: Int = 1,
        @Query("aqi") airQuality: String = "no",
        @Query("alerts") alert: String = "no"
    ): WeatherResponse?

    @GET("search.json")
    suspend fun getSearchAutoComplete(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ): List<SearchAutoCompleteItem>
}