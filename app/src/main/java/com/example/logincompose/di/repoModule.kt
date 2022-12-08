package com.example.logincompose.di

import com.example.logincompose.data.repository.AuthRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single { AuthRepositoryImpl(get()) }


}