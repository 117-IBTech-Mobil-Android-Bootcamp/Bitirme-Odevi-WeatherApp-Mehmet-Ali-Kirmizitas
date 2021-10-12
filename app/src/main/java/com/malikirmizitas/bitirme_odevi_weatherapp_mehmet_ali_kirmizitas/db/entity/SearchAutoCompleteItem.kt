package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity

data class SearchAutoCompleteItem(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)