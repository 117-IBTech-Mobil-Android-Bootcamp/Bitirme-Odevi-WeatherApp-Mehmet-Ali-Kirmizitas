package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.DetailScreen

import androidx.navigation.fragment.navArgs
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.R
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.BaseFragment
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.databinding.FragmentDetailBinding
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeViewModel
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeViewStateModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<HomeViewModel, FragmentDetailBinding>() {
    override val mviewModel: HomeViewModel by viewModel()
    private val adapter = WeatherDetailAdapter()

    private val args: DetailFragmentArgs by navArgs()
    override fun getLayoutID() = R.layout.fragment_detail

    override fun observeLiveData() {
        mviewModel.onForecastWeatherFetched.observe(viewLifecycleOwner, {
            dataBinding.data = it
            dataBinding.executePendingBindings()
            weatherConditionController(it)
            it.getWeather().forecast?.forecastday?.get(0)?.let { it1 -> adapter.setList(it1.hour) }
            dataBinding.weatherRecyclerView.adapter = adapter
        })
    }

    private fun weatherConditionController(it: HomeViewStateModel?) {
        val weatherCondition = it?.getWeather()?.current?.condition?.text

        when {
            weatherCondition!!.contains("Mist") -> dataBinding.detailFragmentBackgroundGif.setImageResource(
                R.drawable.fog
            )
            weatherCondition.contains("rain") -> dataBinding.detailFragmentBackgroundGif.setImageResource(
                R.drawable.light_rain
            )
            weatherCondition.contains("cloud") -> dataBinding.detailFragmentBackgroundGif.setImageResource(
                R.drawable.partly_cloud
            )
            weatherCondition.contains("thunder") -> dataBinding.detailFragmentBackgroundGif.setImageResource(
                R.drawable.thunderstorm
            )
            weatherCondition.contains("Clear") -> dataBinding.detailFragmentBackgroundGif.setImageResource(
                R.drawable.clear
            )
            weatherCondition.contains("Sunny") -> dataBinding.detailFragmentBackgroundGif.setImageResource(
                R.drawable.clear
            )
        }
    }

    override fun prepareView() {
        mviewModel.getForecastWeather(args.cityName)
    }
}