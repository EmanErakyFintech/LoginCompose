package com.example.logincompose.presentation.ui.main

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logincompose.data.common.Resource
import com.example.logincompose.domain.models.UserItem
import com.example.logincompose.domain.useCases.GetMainUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val authRepositoryImpl: GetMainUseCase) : ViewModel() {
    private val _dataObserve = MutableSharedFlow<Resource<UserItem?>>()
    var dataObserver = _dataObserve.asSharedFlow()


    private var _uiState = mutableStateOf(UIStateLogin())
    val uiState: State<UIStateLogin> = _uiState

    val intent = Channel<LoginIntent>(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    fun onEvent(context: Context, eventLogin: UIEventLogin) {

        when (eventLogin) {

            is UIEventLogin.IdentityChange -> {
                _uiState.value = _uiState.value.copy(
                    identity = eventLogin.identity
                )
            }
            is UIEventLogin.PasswordChange -> {
                _uiState.value.password = eventLogin.password
            }
            is UIEventLogin.Submit -> {
                if (inputValidation(context, _uiState.value.identity, _uiState.value.password)) {

                    viewModelScope.launch {
                        intent.send(
                            LoginIntent.CallLogin(
                                _uiState.value.identity,
                                _uiState.value.password
                            )
                        )
                    }
                }

            }
        }
    }

    private fun inputValidation(mContext: Context, idState: String, passState: String): Boolean {
        return if (idState.isEmpty()) {
            Toast.makeText(mContext, "idState", Toast.LENGTH_LONG).show()
            _uiState.value = _uiState.value.copy(
                identityError = false,
                passwordError = true
            )
            false
        } else if (passState.isEmpty()) {
            Toast.makeText(mContext, "passState", Toast.LENGTH_LONG).show()
            _uiState.value = _uiState.value.copy(
                passwordError = false,
                identityError = true
            )
            false
        } else
            true


    }

    private fun handleIntent() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    is LoginIntent.CallLogin -> {
                        login(it.userName, it.pass)
                    }

                }
            }
        }
    }

    private fun login(userName: String, pass: String) {
        viewModelScope.launch {
            _dataObserve.emit(Resource.Loading)

            authRepositoryImpl.login(userName, pass).collect {
                _dataObserve.emit(it)
            }

        }
    }

}