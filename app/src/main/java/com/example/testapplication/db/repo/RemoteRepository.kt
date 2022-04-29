package com.example.testapplication.db.repo

import com.example.testapplication.models.Rates
import java.util.*

interface RemoteRepository {
    suspend fun loadFromRemoteRepos(): List<Rates>
}