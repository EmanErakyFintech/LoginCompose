package com.example.logincompose.data.remote

import com.example.logincompose.domain.models.UserItem
import retrofit2.http.*

interface AuthService {

    @FormUrlEncoded
    @POST
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("device_type") device_type: String = "android",
        @Url url: String = "login",
        @Header("Accept-Language") language: String = "ar"
    ): UserItem


}