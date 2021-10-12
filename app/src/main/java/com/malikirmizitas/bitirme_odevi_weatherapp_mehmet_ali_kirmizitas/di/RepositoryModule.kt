package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.di

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { WeatherRepository(get()) }
}