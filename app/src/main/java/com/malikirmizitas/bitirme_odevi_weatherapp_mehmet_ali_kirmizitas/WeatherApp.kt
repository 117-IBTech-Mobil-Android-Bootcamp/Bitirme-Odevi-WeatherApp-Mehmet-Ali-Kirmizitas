package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas

import android.app.Application
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.di.networkModule
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.di.repositoryModule
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApp)
            modules(repositoryModule, networkModule, viewModelModule)
        }
    }
}