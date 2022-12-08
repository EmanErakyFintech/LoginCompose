package com.example.logincompose

import android.app.Application
import com.example.logincompose.di.appModule
import com.example.logincompose.di.repoModule
import com.example.logincompose.di.serviceModule
import com.example.logincompose.di.viewModelModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, repoModule, viewModelModule, serviceModule))
        }
    }
}