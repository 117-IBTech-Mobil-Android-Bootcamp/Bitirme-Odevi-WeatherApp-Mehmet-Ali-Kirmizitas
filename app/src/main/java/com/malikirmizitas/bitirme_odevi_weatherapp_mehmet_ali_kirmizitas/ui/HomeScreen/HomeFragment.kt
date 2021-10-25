package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeScreen

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.R
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.BaseFragment
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.IBaseRecyclerViewItemClickListener
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.databinding.FragmentHomeBinding
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.Weather
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.network.response.WeatherResponse
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeViewModel
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val mviewModel: HomeViewModel by viewModel()

    override fun getLayoutID() = R.layout.fragment_home
    private var listOfViewPagerItems = arrayListOf<WeatherResponse>()
    private val viewPagerAdapter = ViewPagerAdapter()
    private lateinit var connectionLiveData: ConnectivityLiveData
    private var isConnected = false

    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    override fun observeLiveData() {

        connectionLiveData = ConnectivityLiveData(requireActivity().application)
        connectionLiveData.observe(this, {
            isConnected = it
            if (!it) {
                dataBinding.autoComplete.isClickable = false
                dataBinding.autoComplete.alpha = 0.4F
                dataBinding.autoComplete.hint = "Waiting Connection"
                dataBinding.autoComplete.isFocusableInTouchMode = false
            } else {
                dataBinding.autoComplete.isClickable = true
                dataBinding.autoComplete.alpha = 1F
                dataBinding.autoComplete.hint = "Search a location"
                dataBinding.autoComplete.isFocusableInTouchMode = true
            }
        })
        val date = SimpleDateFormat("yyyy-MM-dd").format(getCurrentDate()).toString()
        mviewModel.weatherForecast.observe(this, { response ->

            if (listOfViewPagerItems.size > 0) {
                dataBinding.viewPager.visible()
                dataBinding.emptyListText.gone()
            } else {
                dataBinding.viewPager.gone()
                dataBinding.emptyListText.visible()
            }
            val region = response!!.getWeather().location.region
            if (!listController(region)) {
                listOfViewPagerItems.add(response.getWeather())
                dataBinding.viewPager.adapter?.notifyDataSetChanged()
            }
            val responseDate = response.getWeather().location.localtime.substring(0, 10)
            if (responseDate != date) {
                updateDB(response.getWeather())
            }
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
                    override fun onClick(clickedObject: String) {
                        if (listController(clickedObject))
                            toastShort("This city is already added")
                        else {
                            mviewModel.getCurrentWeather(clickedObject)
                            toastShort("Successfully added")
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

    @SuppressLint("NotifyDataSetChanged")
    private fun updateDB(weather: WeatherResponse) {
        mviewModel.updateDBItems(Weather(weather.current, weather.location))
        listOfViewPagerItems.remove(weather)
        dataBinding.viewPager.adapter?.notifyDataSetChanged()
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
                if (isConnected) {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(clickedObject)
                    findNavController().navigate(action)
                } else
                    toastShort("negative")
            }

        })
        dataBinding.viewPager.adapter = viewPagerAdapter
        dataBinding.autoComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! > 1 && isConnected)
                    mviewModel.getLocationBySearch(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}