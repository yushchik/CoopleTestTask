package com.example.coopletesttask.presentation.weatherForecast

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.coopletesttask.R
import com.example.coopletesttask.data.model.CityWeatherModel
import com.example.coopletesttask.databinding.FragmentWeatherForecastBinding
import com.example.coopletesttask.domain.network.ResponseStatus
import com.example.coopletesttask.util.Constants.CITY_LAT_KEY
import com.example.coopletesttask.util.Constants.CITY_LON_KEY
import com.example.coopletesttask.util.Constants.CITY_NAME_KEY
import com.example.coopletesttask.util.Constants.WEATHER_DETAILS_KEY
import com.example.coopletesttask.util.formatDate
import com.example.coopletesttask.util.formatDecimals
import com.example.coopletesttask.util.getWeatherIconUrl
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherForecastFragment : Fragment(R.layout.fragment_weather_forecast) {
    private var cityName: String? = null
    private var cityLon: Double = 0.0
    private var cityLat: Double = 0.0
    private val binding: FragmentWeatherForecastBinding by viewBinding()
    private val viewModel: WeatherForecastViewModel by viewModel()
    private lateinit var adapter: WeeklyWeatherAdapter


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityName = it.getString(CITY_NAME_KEY)
            cityLon = it.getDouble(CITY_LON_KEY)
            cityLat = it.getDouble(CITY_LAT_KEY)
        }

        lifecycleScope.launch {
            viewModel.responseStatus.collect {
                when (it) {
                    is ResponseStatus.Success<*> -> {
                        val response = it.data as CityWeatherModel
                        binding.progress.visibility = View.GONE
                        binding.clWeather.visibility = View.VISIBLE
                        binding.tvCityName.text = cityName
                        binding.tvDate.text = formatDate(response.list[0].dt)
                        binding.tvTemp.text = "${formatDecimals(response.list[0].temp.day)}º"
                        Picasso.get()
                            .load(getWeatherIconUrl(response.list[0].weather[0].icon))
                            .into(binding.imgWeather)
                        response.list.removeFirst()
                        adapter.submitList(response.list)
                    }

                    is ResponseStatus.Error -> {}

                    else -> {}
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        lifecycleScope.launch {
            viewModel.getCityWeather(cityLat, cityLon)
        }
    }

    private fun initRecyclerView() {
        adapter = WeeklyWeatherAdapter {
            findNavController().navigate(
                R.id.weatherDetailsFragment,
                bundleOf(
                    WEATHER_DETAILS_KEY to it,
                    CITY_NAME_KEY to cityName
                )
            )
        }
        binding.rvWeather.layoutManager = LinearLayoutManager(context)
        binding.rvWeather.adapter = adapter
        binding.rvWeather.itemAnimator = null
    }
}