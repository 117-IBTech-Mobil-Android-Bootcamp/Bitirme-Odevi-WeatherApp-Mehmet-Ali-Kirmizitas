package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.WeatherDAO
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.WeatherApi
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.Result
import junit.framework.TestCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

open class WeatherRepositoryTest : TestCase() {

    private val api = mock<WeatherApi>()
    private val dao = mock<WeatherDAO>()
    private val response: WeatherResponse = Mockito.mock(WeatherResponse::class.java)

    @Test
    fun `Get forecast from remote function should return success when data not null`() {
        runBlocking {

            val city = "Gaziantep"
            val weatherRepository = WeatherRepository(api, dao)
            whenever(api.getForecast(city = city)).thenReturn(response)
            val weatherForecast: Flow<Result<WeatherResponse?>> =
                weatherRepository.getForecastFromRemote(city)
            weatherForecast.collect {
                assert(it is Result.Success)
            }
        }
    }

    @Test
    fun `Get forecast from remote function should be error when something went wrong`() {
        runBlocking {
            val city = "Gaziantep"
            val weatherRepository = WeatherRepository(api, dao)
            whenever(api.getForecast(city = city)).thenThrow(RuntimeException())
            val weatherForecast: Flow<Result<WeatherResponse?>> =
                weatherRepository.getForecastFromRemote(city)
            weatherForecast.collect {
                assert(it is Result.Error)
            }
        }
    }
}