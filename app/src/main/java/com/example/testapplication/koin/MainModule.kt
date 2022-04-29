package com.example.testapplication.koin

import androidx.room.Room
import com.example.testapplication.Interactor
import com.example.testapplication.api.Repository
import com.example.testapplication.db.repo.DataBase
import com.example.testapplication.db.repo.DataBaseRepository
import com.example.testapplication.db.repo.LocalRepository
import com.example.testapplication.db.repo.RemoteRepository
import com.example.testapplication.ui.ViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object MainModule {

    fun create(): Module = module {
        factory { ViewModel(get()) }
        factory { Interactor(get(), get()) }
        factory { Repository(get()) }
        single {
            Room.databaseBuilder(get(), DataBase::class.java, "dao")
                .build()
        }
        single { get<DataBase>().resultDao() }
        single<LocalRepository> { DataBaseRepository(get()) }
        single<RemoteRepository> { Repository(get()) }
    }
}