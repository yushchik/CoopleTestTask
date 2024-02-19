package com.example.coopletesttask.data.model

import androidx.annotation.Keep
import com.example.coopletesttask.data.model.weatherResponse.WeatherItem

@Keep
data class CityWeatherModel(
    val name: String,
    val list: ArrayList<WeatherItem>,
)