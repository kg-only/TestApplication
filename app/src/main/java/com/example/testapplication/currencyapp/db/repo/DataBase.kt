package com.example.testapplication.currencyapp.db.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapplication.currencyapp.db.dao.Dao
import com.example.testapplication.currencyapp.db.entity.RatesEntity

@Database(entities = [RatesEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
        abstract fun resultDao(): Dao
    }
