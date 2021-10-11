package com.android.mobileweatherapp.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlin.math.floor

@BindingAdapter("android:text")
fun setText(text: TextView, temp: Double) {
    val tempIntValue = floor(temp).toInt()
    val formattedTemp = "$tempIntValue\u00B0"
    text.text = formattedTemp
}