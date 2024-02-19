package com.example.coopletesttask.data.repository

import com.example.coopletesttask.data.model.CityLocationResponse
import com.example.coopletesttask.data.sourse.BackendApi
import kotlinx.coroutines.flow.Flow

interface ICityLocationRepository {

    suspend fun getCityLocation(city: String): Flow<List<CityLocationResponse?>>

}

class CityLocationRepository(
    private val api: BackendApi
) : ICityLocationRepository {

    override suspend fun getCityLocation(city: String): Flow<List<CityLocationResponse?>> =
        api.getCityCoordinates(city)
}
