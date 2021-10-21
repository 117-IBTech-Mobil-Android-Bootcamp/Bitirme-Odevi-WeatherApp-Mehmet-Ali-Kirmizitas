package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _searchResult = MutableLiveData<SearchStateModel>()
    val searchResult: LiveData<SearchStateModel>
        get() = _searchResult

    private val _onForecastWeatherFetched = MutableLiveData<WeatherResponse?>()
    val onForecastWeatherFetched: LiveData<WeatherResponse?>
        get() = _onForecastWeatherFetched

    private val _weatherForecast = MutableLiveData<HomeViewStateModel>()
    val weatherForecast: LiveData<HomeViewStateModel?>
        get() = _weatherForecast

    init {
        getWeatherFromDB()
    }

    fun getCurrentWeather(city: String) {
        viewModelScope.launch {
            weatherRepository.getCurrentFromRemote(city).collect {
                _weatherForecast.value = HomeViewStateModel(it!!)
            }
        }
        getWeatherFromDB()
    }

    fun getForecastWeather(city: String) {
        viewModelScope.launch {
            weatherRepository.getForecastFromRemote(city).collect {
                _onForecastWeatherFetched.value = it
            }
        }
    }

    private fun getWeatherFromDB() {
        viewModelScope.launch {
            delay(1000)
            for (weather in weatherRepository.getListAsync()) {
                _weatherForecast.value =
                    HomeViewStateModel(WeatherResponse(weather.current, null, weather.location))
            }
        }
    }

    fun getLocationBySearch(city: String) {
        viewModelScope.launch {
            weatherRepository.getSearchedLocationWeather(city).collect {
                _searchResult.value = SearchStateModel(it)
            }
        }
    }
}