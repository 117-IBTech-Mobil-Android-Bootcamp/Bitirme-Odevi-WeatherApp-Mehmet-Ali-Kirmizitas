package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db

import androidx.room.Dao

@Dao
interface WeatherDAO {

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: Forecastday)

    @Query("SELECT * FROM Forecast")
    suspend fun fetchWeather(): List<Forecastday>*/
}