package com.example.coopletesttask.data.model.weatherResponse

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class WeatherItem(
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("feels_like")
    val feels_like: FeelsLike,
    @SerializedName("gust")
    val gust: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("rain")
    val rain: Double,
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("temp")
    val temp: Temp,
    @SerializedName("weather")
    val weather: List<WeatherObject>
): Parcelable