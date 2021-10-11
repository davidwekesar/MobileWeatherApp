package com.android.mobileweatherapp.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlin.math.floor

@BindingAdapter("android:text")
fun setText(text: TextView, temp: Double) {
    val tempIntValue = tempToInt(temp)
    val formattedTemp = "$tempIntValue\u00B0"
    text.text = formattedTemp
}

fun tempToInt(temp: Double): Int {
    return floor(temp).toInt()
}