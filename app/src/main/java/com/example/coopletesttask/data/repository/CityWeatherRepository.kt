package com.example.coopletesttask.data.repository

import com.example.coopletesttask.data.model.weatherResponse.Weather
import com.example.coopletesttask.data.sourse.BackendApi
import kotlinx.coroutines.flow.Flow

interface ICityWeatherRepository {
    suspend fun getCityWeather(lat: Double, lon: Double): Flow<Weather>
}

class CityWeatherRepository(
    private val api: BackendApi
) : ICityWeatherRepository {

    override suspend fun getCityWeather(lat: Double, lon: Double): Flow<Weather> =
        api.getWeather(lat, lon)
}