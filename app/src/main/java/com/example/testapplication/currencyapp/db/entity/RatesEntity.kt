package com.example.testapplication.currencyapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testapplication.currencyapp.db.convertor.RoomConvertor

@Entity(tableName = "dao")
@TypeConverters(RoomConvertor::class)
data class RatesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "USD")
    var USD: Double? = null,

    @ColumnInfo(name = "AUD")
    var AUD: Double? = null,

    @ColumnInfo(name = "CAD")
    var CAD: Double? = null,

    @ColumnInfo(name = "PLN")
    var PLN: Double? = null,

    @ColumnInfo(name = "MXN")
    var MXN: Double? = null

)