package com.android.mobileweatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.mobileweatherapp.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeatherBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        binding.viewModel = weatherViewModel
        binding.executePendingBindings()

        return binding.root
    }
}