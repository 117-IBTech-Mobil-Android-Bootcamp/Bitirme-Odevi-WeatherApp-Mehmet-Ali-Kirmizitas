package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.R
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.IBaseRecyclerViewItemClickListener
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.databinding.ViewPagerItemBinding
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Weather
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse

class ViewPagerAdapter(
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private lateinit var cityNames: ArrayList<WeatherResponse>
    private var mListener: IBaseRecyclerViewItemClickListener<Any>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_pager_item,
                parent,
                false
            )
        )
    }

    fun setList(cityNames: ArrayList<WeatherResponse>) {
        this.cityNames = cityNames
    }

    fun setListener(listener: IBaseRecyclerViewItemClickListener<Any>) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val city = cityNames[position]
        holder.init(city)
        holder.setOnClickListener(city, mListener)
    }

    override fun getItemCount() = cityNames.size

    inner class ViewPagerViewHolder(private val binding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun init(data: WeatherResponse) {
            binding.data = data
        }

        fun setOnClickListener(
            data: WeatherResponse,
            itemClickListener: IBaseRecyclerViewItemClickListener<Any>?
        ) {
            binding.detailIcon.setOnClickListener {
                itemClickListener!!.onClickForDetail(data.location.name)
            }
            binding.delete.setOnClickListener {
                itemClickListener!!.onClickForDelete(Weather(data.current, data.location))
            }
        }
    }
}