package com.example.coopletesttask.data.model.weatherResponse

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Weather(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: ArrayList<WeatherItem>,
    @SerializedName("message")
    val message: Double
)