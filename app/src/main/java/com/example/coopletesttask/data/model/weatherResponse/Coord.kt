package com.example.coopletesttask.data.model.weatherResponse

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)