package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class Current(
    @ColumnInfo(name="cloud") val cloud: Int,
    @Embedded val condition: Condition,
    @ColumnInfo(name="feelslike_c") val feelslike_c: Double,
    @ColumnInfo(name="feelslike_f") val feelslike_f: Double,
    @ColumnInfo(name="gust_kph") val gust_kph: Double,
    @ColumnInfo(name="gust_mph") val gust_mph: Double,
    @ColumnInfo(name="humidity") val humidity: Int,
    @ColumnInfo(name="is_day") val is_day: Int,
    @ColumnInfo(name="last_updated") val last_updated: String,
    @ColumnInfo(name="last_updated_epoch") val last_updated_epoch: Int,
    @ColumnInfo(name="precip_in") val precip_in: Double,
    @ColumnInfo(name="precip_mm") val precip_mm: Double,
    @ColumnInfo(name="pressure_in")val pressure_in: Double,
    @ColumnInfo(name="pressure_mb")val pressure_mb: Double,
    @ColumnInfo(name="temp_c")val temp_c: Double,
    @ColumnInfo(name="temp_f")val temp_f: Double,
    @ColumnInfo(name="uv") val uv: Double,
    @ColumnInfo(name="vis_km")val vis_km: Double,
    @ColumnInfo(name="vis_miles")val vis_miles: Double,
    @ColumnInfo(name="wind_degree")val wind_degree: Int,
    @ColumnInfo(name="wind_dir")val wind_dir: String,
    @ColumnInfo(name="wind_kph")val wind_kph: Double,
    @ColumnInfo(name="wind_mph")val wind_mph: Double
)