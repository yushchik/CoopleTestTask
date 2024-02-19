package com.example.coopletesttask.data.model.weatherResponse

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class City(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("timezone")
    val timezone: Int
)
