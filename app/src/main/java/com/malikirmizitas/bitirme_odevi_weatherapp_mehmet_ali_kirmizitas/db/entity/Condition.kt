package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity

import androidx.room.ColumnInfo

data class Condition(
    @ColumnInfo(name = "code") val code: Int,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "text") val text: String
)