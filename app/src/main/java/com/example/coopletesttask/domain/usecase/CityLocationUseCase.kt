package com.example.coopletesttask.domain.usecase

import com.example.coopletesttask.data.repository.ICityLocationRepository
import kotlinx.coroutines.flow.map

class CityLocationUseCase(
    private val cityLocationRepository: ICityLocationRepository,
) {

    suspend fun invoke(city: String) = cityLocationRepository.getCityLocation(city).map { it[0] }

}