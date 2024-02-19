package com.example.coopletesttask.data.model.db

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coopletesttask.util.Constants.TABLE_NAME_CITY

@Keep
@Entity(tableName = TABLE_NAME_CITY)
data class CityTable(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double
)