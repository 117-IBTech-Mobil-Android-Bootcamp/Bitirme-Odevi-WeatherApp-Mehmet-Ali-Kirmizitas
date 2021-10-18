package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Current
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Forecast
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Location

data class WeatherResponse(

    val current: Current,
    val forecast: Forecast?,
    val location: Location
)