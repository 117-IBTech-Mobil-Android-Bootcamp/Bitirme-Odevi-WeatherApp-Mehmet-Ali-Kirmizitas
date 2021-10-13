package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setUrl(imageView: AppCompatImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load("https:$imageUrl")
            .into(imageView)
    }
}