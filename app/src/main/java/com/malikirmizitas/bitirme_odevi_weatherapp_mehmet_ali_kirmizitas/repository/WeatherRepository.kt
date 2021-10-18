package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.WeatherDAO
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.SearchAutoComplete
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Weather
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.WeatherApi
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.API_KEY
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.Result

class WeatherRepository(private val api: WeatherApi,private val weatherDAO: WeatherDAO) {


    suspend fun getCurrentWeatherFromRemote(city: String): Result<WeatherResponse> {
        val response = api.getCurrentCityWeather(API_KEY, city, "no")
        return if (response != null) {
            insertDataAsync(Weather(response.current,response.location))
            Result.Success(response)
        } else
            Result.Error("")
    }

    suspend fun getForecastFromRemote(city: String): Result<WeatherResponse> {
        val response = api.getForecast(API_KEY, city)
        return if (response != null) {
            Result.Success(response)
        } else
            Result.Error("")
    }

    suspend fun getSearchedLocationWeather(city: String): Result<SearchAutoComplete> {
        val response = SearchAutoComplete(api.getSearchAutoComplete(API_KEY, city))
        return Result.Success(response)
    }

    suspend fun insertDataAsync(weather : Weather) = weatherDAO.insertWeather(weather)

    suspend fun getListAsync(): List<Weather> {
        return weatherDAO.fetchWeather()
    }
}