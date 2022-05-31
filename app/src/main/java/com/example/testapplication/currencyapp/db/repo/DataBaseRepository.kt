package com.example.testapplication.currencyapp.db.repo

import com.example.testapplication.currencyapp.db.convertor.RatesConvertor
import com.example.testapplication.currencyapp.db.dao.Dao
import com.example.testapplication.currencyapp.models.Rates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataBaseRepository(private val dao: Dao) : LocalRepository {
    override suspend fun insertLocalRepos(rates: List<Rates>) {
        val entities = rates.map {
            RatesConvertor.toDatabase(it)
        }
        dao.insertAll(entities)
    }

    override suspend fun getRoomLocalRepos(): Flow<List<Rates>> {
        return dao.getAll().map { list ->
            list.map { RatesConvertor.fromDatabase(it) }
        }
    }
}