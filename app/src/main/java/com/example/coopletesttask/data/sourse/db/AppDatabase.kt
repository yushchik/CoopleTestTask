package com.example.coopletesttask.data.sourse.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coopletesttask.data.model.db.CityTable

@Database(entities = [CityTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CityDao(): CityDao
}