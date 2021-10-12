package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.di

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideHttpClient() }
    single { provideWeatherApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //adding logging interceptor to okhttp
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
}

fun provideRetrofit(httpClient: OkHttpClient): Retrofit {

    val BASE_URL = "https://api.weatherapi.com/v1/"

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
}

fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
    return retrofit.create(WeatherApi::class.java)
}