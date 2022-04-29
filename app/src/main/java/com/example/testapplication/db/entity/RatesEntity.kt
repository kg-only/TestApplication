package com.example.testapplication.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testapplication.db.convertor.RoomConvertor

@Entity(tableName = "dao")
@TypeConverters(RoomConvertor::class)
data class RatesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var USD: Double? = null,
    var AUD: Double? = null,
    var CAD: Double? = null,
    var PLN: Double? = null,
    var MXN: Double? = null

)