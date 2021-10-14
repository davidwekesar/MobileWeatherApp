package com.android.mobileweatherapp.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.android.mobileweatherapp.R
import com.android.mobileweatherapp.databinding.ActivityMainBinding
import com.android.mobileweatherapp.viewmodels.Coordinates
import com.android.mobileweatherapp.viewmodels.OpenWeatherApiStatus
import com.android.mobileweatherapp.viewmodels.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val weatherViewModel: WeatherViewModel by viewModels()

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            displayPermissionDialog()
        }
    }

    private val progressDialog = CustomProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.lifecycleOwner = this

        binding.viewModel = weatherViewModel

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        checkLocationPermission()

        openWeatherApiObserver()
    }

    private fun checkLocationPermission() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) -> {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val coordinates = Coordinates(latitude, longitude)
                    weatherViewModel.getWeatherData(coordinates)
                    Timber.i("Latitude: $latitude, Longitude: $longitude")
                }
            }
            else -> {
                locationPermissionRequest.launch(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            }
        }
    }

    private fun openWeatherApiObserver() {
        weatherViewModel.openWeatherApiStatus.observe(this, { openWeatherApiStatus ->
            openWeatherApiStatus?.let {
                when (openWeatherApiStatus) {
                    OpenWeatherApiStatus.LOADING -> {
                        progressDialog.show(this, "Please wait...")
                    }
                    OpenWeatherApiStatus.DONE -> {
                        progressDialog.dialog.dismiss()
                    }
                    OpenWeatherApiStatus.ERROR -> {
                        progressDialog.dialog.dismiss()
                    }
                }
            }
        })
    }

    private fun displayPermissionDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.permission_rationale_title))
            .setMessage(getString(R.string.permission_rationale_message))
    }
}