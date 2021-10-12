package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.ui

import com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.db.entity.SearchAutoComplete

class SearchStateModel(private val response: SearchAutoComplete) {
    fun getResults(): List<String> {
        val nameList = mutableListOf<String>()
        response.response.forEach {
            nameList.add(it.name)
        }
        return nameList
    }
}