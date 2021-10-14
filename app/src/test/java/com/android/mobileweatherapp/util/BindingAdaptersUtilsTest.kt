package com.android.mobileweatherapp.util

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BindingAdaptersUtilsTest {

    @Test
    fun tempToInt_Double_ReturnsInt() {
        val temp = 18.67

        val result = tempToInt(temp)

        assertThat(result, `is`(18))
    }

    @Test
    fun tempToInt_null_ReturnsZero(){
        val result = tempToInt(null)

        assertThat(result, `is`(0))
    }

    @Test
    fun getWeatherName_Int_ReturnsString() {
        val weatherId = 801

        val result = getWeatherName(weatherId)

        assertThat(result, `is`("Cloudy"))
    }

    @Test
    fun getWeatherName_null_ReturnsEmptyString() {
        val result = getWeatherName(null)

        assertThat(result, `is`(""))
    }

    @Test
    fun convertUnixTimeToDay_UnixTime_ReturnsDay() {
        val time = 1618317040L

        val result = convertUnixTimeToDay(time)

        assertThat(result, `is`("Tuesday"))
    }
}