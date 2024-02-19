package com.example.coopletesttask.data.model


import androidx.annotation.Keep
import com.example.coopletesttask.data.model.weatherResponse.LocalNames
import com.google.gson.annotations.SerializedName

@Keep
data class CityLocationResponse(
    @SerializedName("country")
    var country: String,
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("local_names")
    var localNames: LocalNames?,
    @SerializedName("lon")
    var lon: Double,
    @SerializedName("name")
    var name: String,
    @SerializedName("state")
    var state: String
)