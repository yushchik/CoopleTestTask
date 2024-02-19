package com.example.coopletesttask.util

import com.example.coopletesttask.data.model.CityLocationResponse
import com.example.coopletesttask.data.model.CityWeatherModel
import com.example.coopletesttask.data.model.db.CityTable
import com.example.coopletesttask.data.model.weatherResponse.Weather

fun CityLocationResponse.toCityTable() = CityTable(
    name = name,
    latitude = lat,
    longitude = lon
)

fun Weather.toCityWeather() = CityWeatherModel(
    name = city.name,
    list = list
)