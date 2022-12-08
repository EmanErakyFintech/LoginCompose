package com.example.logincompose.domain.useCases

import com.example.logincompose.data.repository.AuthRepository

class GetMainUseCase (private val authRepo: AuthRepository){

    suspend fun login(userName:String, pass:String) =
        authRepo.login(userName , pass)
}