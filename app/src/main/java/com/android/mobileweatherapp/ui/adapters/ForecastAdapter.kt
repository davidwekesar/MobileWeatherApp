package com.android.mobileweatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mobileweatherapp.data.network.DayForecast
import com.android.mobileweatherapp.databinding.ListItemDayForecastBinding
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(
    private val forecastList: List<DayForecast>
) : RecyclerView.Adapter<DayForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemDayForecastBinding.inflate(layoutInflater, parent, false)
        return DayForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayForecastViewHolder, position: Int) {
        val dayForecast = forecastList[position]
        holder.bind(dayForecast)
    }

    override fun getItemCount(): Int = forecastList.size
}

class DayForecastViewHolder(
    private var binding: ListItemDayForecastBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dayForecast: DayForecast) {
        binding.dayForecast = dayForecast
        binding.executePendingBindings()
    }
}

fun getDayName(time: Long): String {
    val sdf = SimpleDateFormat("E", Locale.getDefault())
    val date = Date(time * 1000)
    return sdf.format(date)
}