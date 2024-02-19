package com.example.coopletesttask.domain.usecase

import com.example.coopletesttask.data.repository.ICityWeatherRepository
import com.example.coopletesttask.util.toCityWeather
import kotlinx.coroutines.flow.map

class CityWeatherUseCase(
    private val cityWeatherRepository: ICityWeatherRepository
) {

    suspend fun invoke(lat: Double, lon: Double) = cityWeatherRepository.getCityWeather(lat, lon)
        .map { it.toCityWeather() }

}