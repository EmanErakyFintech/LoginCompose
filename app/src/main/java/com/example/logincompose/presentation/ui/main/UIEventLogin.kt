package com.example.logincompose.presentation.ui.main

sealed class UIEventLogin {
    data class IdentityChange(val identity: String) : UIEventLogin()
    data class PasswordChange(val password: String) : UIEventLogin()
    object Submit: UIEventLogin()
}
