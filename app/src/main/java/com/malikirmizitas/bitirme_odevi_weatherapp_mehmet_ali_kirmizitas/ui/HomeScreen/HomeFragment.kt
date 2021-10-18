package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeScreen

import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.R
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.BaseFragment
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.IBaseRecyclerViewItemClickListener
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.databinding.FragmentHomeBinding
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val mviewModel: HomeViewModel by viewModel()

    override fun getLayoutID() = R.layout.fragment_home
    var listOfViewPagerItems = arrayListOf<WeatherResponse>()

    override fun observeLiveData() {
        listOfViewPagerItems.clear()
        mviewModel.searchResult.observe(viewLifecycleOwner, {
            dataBinding.weatherResponse = it
            dataBinding.executePendingBindings()
            val adapter = AutoCompleteAdapter(
                requireContext(),
                R.layout.city_auto_complete_list_item,
                it.getResults()
            ).also { it1 ->
                it1.setListener(object : IBaseRecyclerViewItemClickListener<String> {
                    override fun onClick(cityName: String) {
                        mviewModel.prepareWeather(cityName)
                    }
                })
            }
            dataBinding.autoComplete.setAdapter(adapter)
        })
        mviewModel.getWeatherFromDB()
        mviewModel.onWeatherFetched.observe(viewLifecycleOwner, { response ->
            listOfViewPagerItems.add(response.getWeather())
        })
    }

    override fun prepareView() {
        val adapter = ViewPagerAdapter(listOfViewPagerItems)
        adapter.setListener(object :
            IBaseRecyclerViewItemClickListener<String> {
            override fun onClick(clickedObject: String) {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(clickedObject)
                findNavController().navigate(action)
            }

        })

        dataBinding.viewPager.adapter = adapter
        dataBinding.autoComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! > 1)
                    mviewModel.getLocationBySearch(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}