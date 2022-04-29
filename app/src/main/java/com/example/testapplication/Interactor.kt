package com.example.testapplication

import com.example.testapplication.api.Repository
import com.example.testapplication.db.repo.LocalRepository
import com.example.testapplication.db.repo.RemoteRepository
import com.example.testapplication.models.Rates
import kotlinx.coroutines.flow.Flow

class Interactor(
    private val localRepository: LocalRepository,
    private val repository: Repository,
) {
    suspend fun getFromInteractor(): Flow<List<Rates>> {
        return localRepository.getRoomLocalRepos()
    }

    suspend fun loadFromInteractor(): List<Rates> {
        val rates = repository.loadFromRemoteRepos()
        localRepository.insertLocalRepos(rates)
        return rates
    }
}