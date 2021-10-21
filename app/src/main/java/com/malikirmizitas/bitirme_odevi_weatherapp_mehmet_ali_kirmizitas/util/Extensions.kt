package com.malikirmizitas.bitirme_odevi_weatherapp_mehmet_ali_kirmizitas.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.*

fun Fragment.toastShort(messageToShow: String) {
    Toast.makeText(requireContext(), messageToShow, Toast.LENGTH_SHORT).show()
}

fun Fragment.toastLong(messageToShow: String) {
    Toast.makeText(requireContext(), messageToShow, Toast.LENGTH_LONG).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
inline fun <reified T : AppCompatActivity> Context.startActivity(block: Intent.() -> Unit = {}) {

    val intent = Intent(this, T::class.java)
    startActivity(
        intent.also {
            block.invoke(it)
        }
    )
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun getCurrentDate(): Date {
    val calendar: Calendar = Calendar.getInstance()
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val month: Int = calendar.get(Calendar.MONTH)
    val year: Int = calendar.get(Calendar.YEAR)

    calendar[year, month] = day

    return calendar.time
}
