package com.android.mobileweatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mobileweatherapp.data.network.DailyForecast
import com.android.mobileweatherapp.databinding.ListItemDailyForecastBinding

class ForecastAdapter(
    private val forecastList: List<DailyForecast>
) : RecyclerView.Adapter<DayForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemDailyForecastBinding.inflate(layoutInflater, parent, false)
        return DayForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayForecastViewHolder, position: Int) {
        val dayForecast = forecastList[position]
        holder.bind(dayForecast)
    }

    override fun getItemCount(): Int = forecastList.size
}

class DayForecastViewHolder(
    private var binding: ListItemDailyForecastBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dailyForecast: DailyForecast) {
        binding.dailyForecast = dailyForecast
        binding.executePendingBindings()
    }
}