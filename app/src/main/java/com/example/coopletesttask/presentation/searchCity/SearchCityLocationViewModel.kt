package com.example.coopletesttask.presentation.searchCity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coopletesttask.data.repository.ICityInfoRepository
import com.example.coopletesttask.domain.network.ResponseStatus
import com.example.coopletesttask.domain.usecase.CityLocationUseCase
import com.example.coopletesttask.util.toCityTable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchCityLocationViewModel(
    private val cityLocationUseCase: CityLocationUseCase,
    private val cityInfoRepository: ICityInfoRepository
) : ViewModel() {
    private val _responseStatus: MutableStateFlow<ResponseStatus> =
        MutableStateFlow(ResponseStatus.Initial)

    val responseStatus: StateFlow<ResponseStatus>
        get() = _responseStatus

    fun findCityLocation(city: String) = viewModelScope.launch {
        try {
            cityLocationUseCase.invoke(city)
                .collect { cityResponse ->
                    Timber.d("RESPONSE $cityResponse")
                    _responseStatus.emit(ResponseStatus.Success(cityResponse))
                    cityInfoRepository.addCity(
                        cityResponse?.run { toCityTable() }!!
                    )
                }
        } catch (e: Exception) {
            _responseStatus.emit(ResponseStatus.Error)
            Timber.d("HttpException SerchCityLocationViewModel")
        }

    }

    fun getCityList() = cityInfoRepository.getCityList()


}