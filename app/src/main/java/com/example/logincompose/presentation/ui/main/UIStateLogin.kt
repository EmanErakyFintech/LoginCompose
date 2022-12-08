package com.example.logincompose.presentation.ui.main

data class UIStateLogin(
    val identity: String = "",
    var password: String = "",
    val identityError: Boolean = false,
    val passwordError: Boolean = false,
)
