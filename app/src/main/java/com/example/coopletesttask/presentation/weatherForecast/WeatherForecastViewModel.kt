package com.example.coopletesttask.presentation.weatherForecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coopletesttask.domain.network.ResponseStatus
import com.example.coopletesttask.domain.usecase.CityWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class WeatherForecastViewModel(
    private val cityWeatherUseCase: CityWeatherUseCase
) : ViewModel() {

    private val _responseStatus: MutableStateFlow<ResponseStatus> =
        MutableStateFlow(ResponseStatus.Initial)

    val responseStatus: StateFlow<ResponseStatus>
        get() = _responseStatus

    fun getCityWeather(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            cityWeatherUseCase.invoke(lat, lon)
                .collect { response ->
                    Timber.d("RESPONSE $response")
                    _responseStatus.emit(ResponseStatus.Success(response))
                }
        } catch (e: Exception) {
            _responseStatus.emit(ResponseStatus.Error)
            Timber.d("HttpException WeatherForecastViewModel")
        }
    }

}