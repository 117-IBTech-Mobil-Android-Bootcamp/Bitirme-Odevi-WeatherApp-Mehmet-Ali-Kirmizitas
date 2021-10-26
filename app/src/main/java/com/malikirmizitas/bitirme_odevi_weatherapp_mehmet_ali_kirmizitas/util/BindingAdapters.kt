package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setUrl(imageView: AppCompatImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load("https:$imageUrl")
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("humidityText")
    fun setHumidityText(textView: AppCompatTextView,humidityText : String?){
        textView.text = humidityText.plus("%")
    }

    @JvmStatic
    @BindingAdapter("degreeCelsius")
    fun setCelsiusText(textView: AppCompatTextView,text : String?){
        textView.text = text.plus("°C")
    }

    @JvmStatic
    @BindingAdapter("gustWithKPH")
    fun setGustText(textView: AppCompatTextView,text : String?){
        textView.text = text.plus(" kph")
    }

    @JvmStatic
    @BindingAdapter("pressureWithText")
    fun setPressureText(textView: AppCompatTextView,text : String?){
        textView.text = text.plus(" hPa")
    }

    @JvmStatic
    @BindingAdapter("degreeFahrenheit")
    fun setFahrenheitText(textView: AppCompatTextView,text: String?){
        textView.text = text.plus("°F")
    }
}