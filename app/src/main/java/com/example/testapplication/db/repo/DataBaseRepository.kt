package com.example.testapplication.db.repo

import com.example.testapplication.db.convertor.RatesConvertor
import com.example.testapplication.db.dao.Dao
import com.example.testapplication.models.Rates
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