package com.example.testapplication.currencyapp.db.repo

import com.example.testapplication.currencyapp.models.Rates
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertLocalRepos(rates: List<Rates>)
    suspend fun getRoomLocalRepos(): Flow<List<Rates>>
}