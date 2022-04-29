package com.example.testapplication.api

import com.example.testapplication.api.Api
import com.example.testapplication.db.repo.RemoteRepository
import com.example.testapplication.models.Currency
import com.example.testapplication.models.Rates

class Repository(private val api: Api) : RemoteRepository {
    override suspend fun loadFromRemoteRepos(): List<Rates> {
        return listOf(api.getCurrency().rates!!)
    }
}