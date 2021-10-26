package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse

class HomeViewStateModel(private val weatherResponse: WeatherResponse) {
    fun getWeather(): WeatherResponse = weatherResponse
}