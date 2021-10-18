package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Forecast
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Weather

@Database(entities = [Weather::class], version = 1)
abstract class WeatherRoom : RoomDatabase() {

    abstract fun WeatherDAO(): WeatherDAO
}