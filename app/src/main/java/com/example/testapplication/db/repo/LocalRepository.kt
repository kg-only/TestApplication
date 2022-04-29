package com.example.testapplication.db.repo

import com.example.testapplication.models.Rates
import kotlinx.coroutines.flow.Flow
import java.util.*

interface LocalRepository {
    suspend fun insertLocalRepos(rates: List<Rates>)
    suspend fun getRoomLocalRepos(): Flow<List<Rates>>
}