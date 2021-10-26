package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui.HomeScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.R
import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base.IBaseRecyclerViewItemClickListener

class AutoCompleteAdapter(
    context: Context,
    textViewResourceId: Int,
    searchResults: List<String>
) : ArrayAdapter<String>(context, 0, textViewResourceId, searchResults) {

    private var mListener: IBaseRecyclerViewItemClickListener<String>? = null
    private var dataList: List<String>? = searchResults

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var adapterView = view

        // using Custom XML View
        if (adapterView == null) {
            adapterView = LayoutInflater.from(parent.context)
                .inflate(R.layout.city_auto_complete_list_item, parent, false)
        }

        val textView = adapterView!!.findViewById(R.id.text1) as TextView
        textView.text = getItem(position)

        // Custom OnClickListener Setup
        textView.setOnClickListener {
            mListener?.onClickForDetail(textView.text.toString())
        }

        return adapterView
    }

    // Custom OnClickListener Setup
    fun setListener(listener: IBaseRecyclerViewItemClickListener<String>) {
        mListener = listener
    }

    // Custom Adapter Setup for AutoCompleteTextView
    override fun getCount() = dataList!!.size

    override fun getItem(position: Int) = dataList!![position]
}