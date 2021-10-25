package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository.WeatherRepository
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.Result
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {

    @get:Rule
    val rule = CoroutineTestRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val weatherResponse = mock<WeatherResponse>()

    private val repository = mock<WeatherRepository>()

    private val weatherObserver = mock<Observer<WeatherResponse?>>()

    private val isErrorObserver = mock<Observer<Boolean>>()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun settingUp() {
        homeViewModel = HomeViewModel(repository).apply {
            onForecastWeatherFetched.observeForever(weatherObserver)
            onError.observeForever(isErrorObserver)
        }
    }

    @Test
    fun `get Current Weather Function Should Be Error when Something went wrong`() =
        rule.runBlockingTest {

            val result = Result.Error(RuntimeException())
            val channel = Channel<Result<WeatherResponse>>()
            val city = "Gaziantep"
            val flow = channel.consumeAsFlow()
            doReturn(flow).whenever(repository).getForecastFromRemote(city)

            launch {
                channel.send(result)
            }

            homeViewModel.getForecastWeather(city)

            verify(isErrorObserver).onChanged(true)
        }

    @Test
    fun `get Current Weather Function Should Be Success when everything OK`() =
        rule.runBlockingTest {

            val result = Result.Success(weatherResponse)
            val channel = Channel<Result<WeatherResponse>>()
            val city = "Gaziantep"
            val flow = channel.consumeAsFlow()
            doReturn(flow).whenever(repository).getForecastFromRemote(city)

            launch {
                channel.send(result)
            }

            homeViewModel.getForecastWeather(city)

            verify(weatherObserver).onChanged(weatherResponse)
        }
}