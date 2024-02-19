package com.example.coopletesttask.data.repository

import com.example.coopletesttask.data.model.db.CityTable
import com.example.coopletesttask.data.sourse.db.AppDatabase
import kotlinx.coroutines.flow.Flow


interface ICityInfoRepository {

    fun getCityList(): Flow<List<CityTable>>

    fun getCityInfo(cityName: String): Flow<CityTable>

    suspend fun addCity(cityTable: CityTable)

}

class CityInfoRepository(
    private val appDatabase: AppDatabase,
) : ICityInfoRepository {

    override fun getCityList() = appDatabase.CityDao().getAll()

    override fun getCityInfo(cityName: String) = appDatabase.CityDao().getCityInfo(cityName)

    override suspend fun addCity(cityTable: CityTable) {
        appDatabase.CityDao().insert(cityTable)
    }
}