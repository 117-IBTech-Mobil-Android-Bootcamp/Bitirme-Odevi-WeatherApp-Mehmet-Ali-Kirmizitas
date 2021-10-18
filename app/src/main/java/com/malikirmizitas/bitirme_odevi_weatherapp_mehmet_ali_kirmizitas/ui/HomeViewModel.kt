package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository.WeatherRepository
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.Result
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val onWeatherFetched = MutableLiveData<HomeViewStateModel>()
    val onForecastWeatherFetched = MutableLiveData<HomeViewStateModel>()
    private val onError = MutableLiveData<Unit>()
    val searchResult = MutableLiveData<SearchStateModel>()

    fun getWeatherFromDB() {
        viewModelScope.launch {
            for (weather in weatherRepository.getListAsync()) {
                onWeatherFetched.value =
                    HomeViewStateModel(WeatherResponse(weather.current, null, weather.location))
            }
        }
    }

    fun prepareWeather(city: String) {
        viewModelScope.launch {
            when (val remoteResponse = weatherRepository.getCurrentWeatherFromRemote(city)) {
                is Result.Success -> {
                    onWeatherFetched.value = HomeViewStateModel(remoteResponse.data!!)
                }
                is Result.Error -> onError.value = Unit
            }
        }
    }

    fun getForecastWeather(city: String) {
        viewModelScope.launch {
            when (val remoteResponse = weatherRepository.getForecastFromRemote(city)) {
                is Result.Success -> {
                    onForecastWeatherFetched.value = HomeViewStateModel(remoteResponse.data!!)
                }
                is Result.Error -> onError.value = Unit
            }
        }
    }

    fun getLocationBySearch(city: String) {
        viewModelScope.launch {

            when (val response = weatherRepository.getSearchedLocationWeather(city)) {
                is Result.Success -> {
                    searchResult.value = SearchStateModel(response.data!!)
                }
                is Result.Error -> {
                    onError.value = Unit
                }
            }
        }
    }
}