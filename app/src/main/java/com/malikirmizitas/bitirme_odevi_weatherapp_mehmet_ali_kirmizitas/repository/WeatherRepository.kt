package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.WeatherDAO
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.SearchAutoComplete
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Weather
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.WeatherApi
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepository(private val api: WeatherApi, private val weatherDAO: WeatherDAO) {

    fun getForecastFromRemote(city: String): Flow<WeatherResponse?> = flow {
        val response = api.getForecast(city = city)
        if (response != null)
            emit(response)
    }.flowOn(Dispatchers.IO)

    fun getCurrentFromRemote(city: String): Flow<WeatherResponse?> = flow {
        val response = api.getCurrentCityWeather(city = city)
        if (response != null) {
            emit(response)
            insertDataAsync(Weather(response.current, response.location))
        }
    }.flowOn(Dispatchers.IO)

    fun getSearchedLocationWeather(city: String): Flow<SearchAutoComplete> = flow {
        val response = SearchAutoComplete(api.getSearchAutoComplete(city = city))
        emit(response)
    }.flowOn(Dispatchers.IO)

    private suspend fun insertDataAsync(weather: Weather) = weatherDAO.insertWeather(weather)

    suspend fun getListAsync(): List<Weather> {
        return weatherDAO.fetchWeather()
    }
}