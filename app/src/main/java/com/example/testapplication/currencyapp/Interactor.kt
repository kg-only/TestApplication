package com.example.testapplication.currencyapp

import com.example.testapplication.currencyapp.db.repo.LocalRepository
import com.example.testapplication.currencyapp.db.repo.RemoteRepository
import com.example.testapplication.currencyapp.models.Rates
import kotlinx.coroutines.flow.Flow

class Interactor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
) {
    suspend fun getFromInteractor(): Flow<List<Rates>> {
        return localRepository.getRoomLocalRepos()
    }

    suspend fun loadFromInteractor(): List<Rates> {
        val rates = remoteRepository.loadFromRemoteRepos()
        localRepository.insertLocalRepos(rates)
        return rates
    }
    suspend fun loadTest(map :Map<String,String>){
        remoteRepository.test(map)
    }
}