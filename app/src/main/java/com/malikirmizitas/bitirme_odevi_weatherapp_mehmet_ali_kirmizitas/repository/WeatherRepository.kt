package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.WeatherDAO
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.WeatherApi
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.API_KEY
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.Result
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.SearchAutoComplete

class WeatherRepository(private val api: WeatherApi) {


    suspend fun getCurrentWeatherFromRemote(): Result<WeatherResponse> {
        return api.getCurrentCityWeather(API_KEY, "london", "no")
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