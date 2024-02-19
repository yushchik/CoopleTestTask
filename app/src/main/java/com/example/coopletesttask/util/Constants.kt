package com.example.coopletesttask.util

import com.example.coopletesttask.BuildConfig

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/"
    const val API_KEY = BuildConfig.API_KEY
    const val TABLE_NAME_CITY = "CityTable"
    const val CITY_LOCATION_DATABASE = "coople_task_db"
    const val CITY_NAME_KEY = "city_name_key"
    const val CITY_LAT_KEY = "city_lat_key"
    const val CITY_LON_KEY = "city_lon_key"
    const val WEATHER_DETAILS_KEY = "weather_details_key"
}