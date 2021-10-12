package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity

data class Location(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
)