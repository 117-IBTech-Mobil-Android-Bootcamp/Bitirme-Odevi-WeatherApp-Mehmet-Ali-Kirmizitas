package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.di

import androidx.room.Room
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.WeatherRoom
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "DB"
val dbModule = module {
    //Room Database
    single {
        Room.databaseBuilder(androidContext(), WeatherRoom::class.java, DATABASE_NAME).build()
    }
    //WeatherDAO
    single { get<WeatherRoom>().WeatherDAO() }
}