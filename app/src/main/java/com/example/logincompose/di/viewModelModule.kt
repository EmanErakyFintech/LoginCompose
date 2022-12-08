package com.example.logincompose.di

import com.example.logincompose.data.repository.AuthRepository
import com.example.logincompose.data.repository.AuthRepositoryImpl
import com.example.logincompose.domain.useCases.GetMainUseCase
import com.example.logincompose.presentation.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    single<AuthRepository> { AuthRepositoryImpl(get ()) }
    single { GetMainUseCase(get ()) }
    viewModel { MainViewModel(get()) }
}