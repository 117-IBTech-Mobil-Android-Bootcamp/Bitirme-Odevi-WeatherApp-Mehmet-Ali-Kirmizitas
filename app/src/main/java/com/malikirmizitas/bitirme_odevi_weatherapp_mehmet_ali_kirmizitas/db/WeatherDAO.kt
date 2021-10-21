package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db

import androidx.room.*
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Weather

@Dao
interface WeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: Weather)

    @Query("SELECT * FROM Forecast")
    suspend fun fetchWeather(): List<Weather>

    @Delete
    suspend fun deleteCity(weather: Weather)
}
