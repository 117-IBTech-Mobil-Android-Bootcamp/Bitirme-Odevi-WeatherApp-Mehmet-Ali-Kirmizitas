package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "forecast")
data class Weather(
    @Embedded val current: Current,
    @PrimaryKey @Embedded val location: Location
)