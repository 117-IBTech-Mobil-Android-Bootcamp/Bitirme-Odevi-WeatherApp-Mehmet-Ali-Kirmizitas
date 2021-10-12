package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeScreen

import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.R
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.BaseFragment
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val mviewModel: HomeViewModel by viewModel()

    override fun getLayoutID() = R.layout.fragment_home

    override fun observeLiveData() {
        mviewModel.searchResult.observe(viewLifecycleOwner, {
            dataBinding.weatherResponse = it
            dataBinding.executePendingBindings()
            dataBinding.autoComplete.setAdapter(activity?.let { arrayAdapter ->
                ArrayAdapter(
                    arrayAdapter,
                    android.R.layout.simple_list_item_1,
                    it.getResults()
                )
            })
        })
    }

    override fun prepareView() {

        dataBinding.autoComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length >= 3)
                    mviewModel.getLocationBySearch(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }

}