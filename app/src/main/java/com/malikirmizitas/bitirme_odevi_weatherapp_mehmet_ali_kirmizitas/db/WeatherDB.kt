package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class WeatherDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "temp_c") val temp_c: Double
)