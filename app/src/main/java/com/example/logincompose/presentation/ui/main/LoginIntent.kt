package com.example.logincompose.presentation.ui.main


sealed class LoginIntent{
    data class CallLogin(val userName:String, val pass:String) : LoginIntent()
}
