package com.example.coopletesttask.data.sourse.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coopletesttask.data.model.db.CityTable
import com.example.coopletesttask.util.Constants.TABLE_NAME_CITY
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * FROM $TABLE_NAME_CITY")
    fun getAll(): Flow<List<CityTable>>

    @Query("SELECT * FROM $TABLE_NAME_CITY WHERE name = :name")
    fun getCityInfo(name: String): Flow<CityTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(locationTable: CityTable)
}