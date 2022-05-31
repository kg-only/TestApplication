package com.example.testapplication.currencyapp.koin
import android.app.Application
import com.example.testapplication.currencyapp.api.ApiBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@Application)
            modules(MainModule.create(), ApiBuilder.create())
        }
    }
}