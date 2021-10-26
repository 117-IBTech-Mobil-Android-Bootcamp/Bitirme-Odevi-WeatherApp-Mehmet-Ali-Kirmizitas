package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.base

interface IBaseRecyclerViewItemClickListener<T> {
    fun onClickForDetail(clickedObject : T)
    fun onClickForDelete(clickedObject : T)
}