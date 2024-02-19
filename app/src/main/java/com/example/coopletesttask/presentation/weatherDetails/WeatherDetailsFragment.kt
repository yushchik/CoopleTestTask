package com.example.coopletesttask.presentation.weatherDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.coopletesttask.R
import com.example.coopletesttask.data.model.weatherResponse.WeatherItem
import com.example.coopletesttask.databinding.FragmentWeatherDetailsBinding
import com.example.coopletesttask.util.Constants
import com.example.coopletesttask.util.formatDate
import com.example.coopletesttask.util.formatDateTime
import com.example.coopletesttask.util.formatDecimals
import com.example.coopletesttask.util.getParcelableCompat
import com.example.coopletesttask.util.getWeatherIconUrl
import com.squareup.picasso.Picasso


class WeatherDetailsFragment : Fragment(R.layout.fragment_weather_details) {
    private var cityName: String? = null

    private val weatherDetails: WeatherItem by lazy { getParcelableCompat<WeatherItem>(Constants.WEATHER_DETAILS_KEY) }
    private val binding: FragmentWeatherDetailsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityName = it.getString(Constants.CITY_NAME_KEY)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvCityName.text = cityName
            tvDate.text = formatDate(weatherDetails.dt)
            tvTempDay.text = "${formatDecimals(weatherDetails.temp.day)}º"
            tvSunrise.text = formatDateTime(weatherDetails.sunrise)
            tvSunset.text = formatDateTime(weatherDetails.sunset)
            tvTempNight.text = "${formatDecimals(weatherDetails.temp.night)}º"
            tvFeelsLike.text = "${formatDecimals(weatherDetails.feels_like.day)}º"
            tvHumidity.text = "${weatherDetails.humidity}%"
            tvPressure.text = "${weatherDetails.pressure} psi"
            tvUv.text = "${weatherDetails.gust} m/s"
        }
        Picasso.get()
            .load(getWeatherIconUrl(weatherDetails.weather[0].icon))
            .into(binding.imgWeather)

    }
}