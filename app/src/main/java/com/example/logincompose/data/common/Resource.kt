package com.example.logincompose.data.common


sealed class Resource<out T> {
    object Loading : Resource<Nothing>()

    data class Success<out T>(val data: T?, val successMessage: String = "") : Resource<T>()

    data class Error(val exception: String? = null) : Resource<Nothing>()
}