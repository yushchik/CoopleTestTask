package com.example.coopletesttask.data.sourse

import com.example.coopletesttask.data.model.CityLocationResponse
import com.example.coopletesttask.data.model.weatherResponse.Weather
import com.example.coopletesttask.util.Constants.API_KEY
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import kotlinx.coroutines.flow.Flow

interface BackendApi {
    @GET(value = "data/2.5/forecast/daily")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int = 7,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en",
        @Query("appid") appid: String = API_KEY
    ): Flow<Weather>

    @GET(value = "geo/1.0/direct")
    fun getCityCoordinates(
        @Query("q") query: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") appid: String = API_KEY
    ): Flow<List<CityLocationResponse>>
}