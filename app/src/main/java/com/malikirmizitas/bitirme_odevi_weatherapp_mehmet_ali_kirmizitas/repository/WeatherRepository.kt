package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.SearchAutoComplete
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.WeatherApi
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.API_KEY
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.Result

class WeatherRepository(private val api: WeatherApi) {


    suspend fun getCurrentWeatherFromRemote(city: String): Result<WeatherResponse> {
        val response = api.getCurrentCityWeather(API_KEY, city, "no")
        return if (response != null) {
            Result.Success(response)
        } else
            Result.Error("Bir hata oluştu")
    }

    suspend fun getSearchedLocationWeather(city: String): Result<SearchAutoComplete> {
        val response = SearchAutoComplete(api.getSearchAutoComplete(API_KEY, city))
        return Result.Success(response)
    }

    /*suspend fun getListAsync(): WeatherResponse {
        //val weather = weatherDAO.fetchWeather()
        return WeatherResponse(weather)
    }*/
}