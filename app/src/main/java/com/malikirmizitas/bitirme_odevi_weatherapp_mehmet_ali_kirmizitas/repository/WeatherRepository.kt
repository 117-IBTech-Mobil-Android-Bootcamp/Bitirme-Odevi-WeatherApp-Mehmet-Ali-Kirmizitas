package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.WeatherDAO
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.SearchAutoComplete
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Weather
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.WeatherApi
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepository(private val api: WeatherApi, private val weatherDAO: WeatherDAO) {

    fun getForecastFromRemote(city: String) = flow {
        try {
            val response = api.getForecast(city = city)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    fun getCurrentFromRemote(city: String) = flow {
        val response = api.getCurrentCityWeather(city = city)
        if (response != null) {
            emit(response)
            insertDataAsync(Weather(response.current, response.location))
        }
    }.flowOn(Dispatchers.IO)

    fun getSearchedLocationWeather(city: String)= flow {
        val response = SearchAutoComplete(api.getSearchAutoComplete(city = city))
        emit(response)
    }.flowOn(Dispatchers.IO)

    suspend fun deleteItemAsync(weather: Weather) = weatherDAO.deleteCity(weather)

    private suspend fun insertDataAsync(weather: Weather) = weatherDAO.insertWeather(weather)

    suspend fun getListAsync(): List<Weather> {
        return weatherDAO.fetchWeather()
    }
}