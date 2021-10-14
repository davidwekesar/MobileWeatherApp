package com.android.mobileweatherapp.util

import org.junit.Assert.*

import org.junit.Test

class BindingAdaptersKtTest {

    @Test
    fun tempToInt_Double_ReturnsInt() {
        val temp = 18.63

        val formattedTemp = tempToInt(temp)

        assertEquals(18, formattedTemp)
    }

    @Test
    fun tempToInt_null_ReturnsZero() {
        val formattedTemp = tempToInt(null)

        assertEquals(0, formattedTemp)
    }

    @Test
    fun getWeatherName_Int_ReturnsString() {
        val weatherId = 530

        val weatherName = getWeatherName(weatherId)

        assertEquals("Rain", weatherName)
    }

    @Test
    fun getWeatherName_null_ReturnsEmptyString() {
        val weatherName = getWeatherName(null)

        assertEquals("", weatherName)
    }

    @Test
    fun getWeatherName_notInRange_ReturnsEmptyString() {
        val weatherId = 601

        val weatherName = getWeatherName(weatherId)

        assertEquals("", weatherName)
    }

    @Test
    fun getWeatherCondition_Int_ReturnsWeatherCondition() {
        val weatherId = 500

        val weatherCondition = getWeatherCondition(weatherId)

        assertEquals(WeatherCondition.RAINY, weatherCondition)
    }
}