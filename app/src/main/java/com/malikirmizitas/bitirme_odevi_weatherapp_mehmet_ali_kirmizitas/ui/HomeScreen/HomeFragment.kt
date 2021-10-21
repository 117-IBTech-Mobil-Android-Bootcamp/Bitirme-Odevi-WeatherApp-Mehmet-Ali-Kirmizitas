package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeScreen

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.R
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.BaseFragment
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.IBaseRecyclerViewItemClickListener
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.databinding.FragmentHomeBinding
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeViewModel
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.gone
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.hideKeyboard
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.toastShort
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val mviewModel: HomeViewModel by viewModel()

    override fun getLayoutID() = R.layout.fragment_home
    var listOfViewPagerItems = arrayListOf<WeatherResponse>()
    private val viewPagerAdapter = ViewPagerAdapter()

    override fun observeLiveData() {
        mviewModel.weatherForecast.observe(this, {response->
            Log.e("Data is", response?.getWeather()?.location!!.name)
            if (listOfViewPagerItems.size > 0) {
                dataBinding.viewPager.visible()
                dataBinding.emptyListText.gone()
            }
            val region = response.getWeather().location.region
            if (!listController(region))
                listOfViewPagerItems.add(response.getWeather())
        })

        mviewModel.searchResult.observe(viewLifecycleOwner, {
            dataBinding.weatherResponse = it
            dataBinding.executePendingBindings()
            val adapter = AutoCompleteAdapter(
                requireContext(),
                R.layout.city_auto_complete_list_item,
                it.getResults()
            ).also { it1 ->
                it1.setListener(object : IBaseRecyclerViewItemClickListener<String> {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onClick(cityName: String) {
                        if (listController(cityName))
                            toastShort("This city is already added")
                        else {
                            mviewModel.getCurrentWeather(cityName)
                            toastShort("Successfully added")
                            viewPagerAdapter.notifyDataSetChanged()
                        }
                        hideKeyboard()
                        clearText()
                    }

                    private fun clearText() {
                        dataBinding.autoComplete.text.clear()
                    }
                })
            }
            dataBinding.autoComplete.setAdapter(adapter)
        })
    }

    private fun listController(region: String): Boolean {
        var isInList = false
        for (item in listOfViewPagerItems) {
            if (region.contains(item.location.region)) {
                isInList = true
                break
            }
        }
        return isInList
    }

    override fun prepareView() {
        viewPagerAdapter.setList(listOfViewPagerItems)
        viewPagerAdapter.setListener(object :
            IBaseRecyclerViewItemClickListener<String> {
            override fun onClick(clickedObject: String) {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(clickedObject)
                findNavController().navigate(action)
            }

        })
        dataBinding.viewPager.adapter = viewPagerAdapter
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