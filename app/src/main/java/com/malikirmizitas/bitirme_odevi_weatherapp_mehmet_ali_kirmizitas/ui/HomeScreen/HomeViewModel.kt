package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.repository.WeatherRepository
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.SearchStateModel
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.Result
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val onWeatherFetched = MutableLiveData<HomeViewStateModel>()
    val onError = MutableLiveData<Unit>()
    val searchResult = MutableLiveData<SearchStateModel>()

    fun prepareWeather() {
        viewModelScope.launch {
            val remoteResponse = weatherRepository.getCurrentWeatherFromRemote()
            when (remoteResponse) {
                is Result.Success -> {
                    onWeatherFetched.value = HomeViewStateModel(remoteResponse.data!!)
                }
                is Result.Error -> onError.value = Unit
            }
        }
    }

    fun getLocationBySearch(city: String) {
        viewModelScope.launch {
            /* weatherRepository.getSearchedLocationWeather(city)
             searchResult.value = */

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