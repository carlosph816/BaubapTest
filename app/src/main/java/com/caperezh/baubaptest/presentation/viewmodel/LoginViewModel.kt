package com.caperezh.baubaptest.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caperezh.baubaptest.common.DataState
import com.caperezh.baubaptest.domain.LoginCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginCase: LoginCaseImpl
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState
    var showDialog by mutableStateOf(false)
        private set
    var showLoader by mutableStateOf(false)
        private set

    fun login() = viewModelScope.launch {
        loginCase.invoke().collect { response ->
            showLoader = response is DataState.Loading
            when (response) {
                is DataState.Success -> {
                    showDialog = true
                    _loginState.value = LoginState.Success
                }

                is DataState.Error -> {
                    _loginState.value = LoginState.Error(
                        response.error.cause
                    )
                }

                else -> {}
            }
        }
    }

    fun dismissDialog() {
        showDialog = false
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}