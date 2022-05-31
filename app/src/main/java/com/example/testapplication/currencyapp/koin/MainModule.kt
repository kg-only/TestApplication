package com.example.testapplication.currencyapp.koin

import androidx.room.Room
import com.example.testapplication.currencyapp.Interactor
import com.example.testapplication.currencyapp.api.Repository
import com.example.testapplication.currencyapp.db.repo.DataBase
import com.example.testapplication.currencyapp.db.repo.DataBaseRepository
import com.example.testapplication.currencyapp.db.repo.LocalRepository
import com.example.testapplication.currencyapp.db.repo.RemoteRepository
import com.example.testapplication.currencyapp.ui.ViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object MainModule {

    fun create(): Module = module {

        factory { ViewModel(get()) }
        factory { Interactor(get(), get()) }
        single {
            Room.databaseBuilder(get(), DataBase::class.java, "dao")
                .build()
        }
        single { get<DataBase>().resultDao() }
        single<LocalRepository> { DataBaseRepository(get()) }
        single<RemoteRepository> { Repository(get()) }
    }
}