package com.example.testapplication.currencyapp.db.repo

import com.example.testapplication.currencyapp.models.Rates

interface RemoteRepository {
    suspend fun loadFromRemoteRepos(): List<Rates>
    suspend fun test(map :Map<String,String>)
}