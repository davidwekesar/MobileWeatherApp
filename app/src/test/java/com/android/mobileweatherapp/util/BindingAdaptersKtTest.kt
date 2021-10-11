package com.android.mobileweatherapp.util

import org.junit.Assert.*

import org.junit.Test

class BindingAdaptersKtTest {

    @Test
    fun tempToInt_TemperatureToInt_ReturnsInt() {

        val temp = 18.63

        val formattedTemp = tempToInt(temp)

        assertEquals(18, formattedTemp)
    }
}