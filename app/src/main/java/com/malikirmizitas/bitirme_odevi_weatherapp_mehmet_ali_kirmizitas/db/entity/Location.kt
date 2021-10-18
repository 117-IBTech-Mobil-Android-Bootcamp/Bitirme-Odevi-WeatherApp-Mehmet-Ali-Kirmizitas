package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity

import androidx.room.ColumnInfo

data class Location(
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "localtime") val localtime: String,
    @ColumnInfo(name = "localtime_epoch") val localtime_epoch: Int,
    @ColumnInfo(name = "lon") val lon: Double,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "region") val region: String,
    @ColumnInfo(name = "tz_id") val tz_id: String
)