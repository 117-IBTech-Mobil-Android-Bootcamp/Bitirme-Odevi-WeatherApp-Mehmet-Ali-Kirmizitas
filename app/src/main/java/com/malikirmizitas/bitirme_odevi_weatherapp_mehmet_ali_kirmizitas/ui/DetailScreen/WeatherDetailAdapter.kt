package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.DetailScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.R
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.databinding.ForecastWeatherItemBinding
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Hour

class WeatherDetailAdapter :
    RecyclerView.Adapter<WeatherDetailAdapter.WeatherViewHolder>() {

    private var weatherList: List<Hour> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.forecast_weather_item,
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(weatherList: List<Hour>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.init(weather)
    }

    override fun getItemCount() = weatherList.size

    class WeatherViewHolder(private val binding: ForecastWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun init(weather: Hour) {
            binding.weather = weather
            binding.executePendingBindings()
        }
    }
}
