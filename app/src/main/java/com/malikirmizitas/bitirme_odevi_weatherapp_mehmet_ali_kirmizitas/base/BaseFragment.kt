package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeScreen.HomeActivity

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding> : Fragment(), FragmentActions {

    abstract val mviewModel: VM
    protected lateinit var dataBinding: DB


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as HomeActivity
        activity.window.statusBarColor =
            ContextCompat.getColor(requireContext(), getStatusBarColor())
        prepareView()
        observeLiveData()

    }

    abstract fun getStatusBarColor(): Int
    abstract fun getLayoutID(): Int
    abstract fun observeLiveData()
    abstract fun prepareView()
    override fun shouldCheckInternetConnection() = true
}