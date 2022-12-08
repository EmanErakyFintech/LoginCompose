package com.example.logincompose.data.repository

import com.example.logincompose.data.common.Resource
import com.example.logincompose.data.remote.AuthService
import com.example.logincompose.domain.models.UserItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Flow<Resource<UserItem>>
}

class AuthRepositoryImpl(private val requestServices: AuthService) : AuthRepository {
    override suspend fun login(username: String, password: String): Flow<Resource<UserItem>> =
        flow {
            emit(Resource.Loading)
            val resource = try {
                val login = requestServices.login(
                    username = username,
                    password = password
                )
                Resource.Success(login)
            } catch (e: Throwable) {
                Resource.Error(e.toString())
            }
            emit(resource)
        }


}
