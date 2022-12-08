package com.example.logincompose.di

import com.example.logincompose.data.remote.AuthService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    single { getAuthServices(get()) }
}

private fun getAuthServices(retrofit: Retrofit) : AuthService =
    retrofit.create(AuthService::class.java)
