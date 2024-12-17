package com.example.myapplication.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.LoginResponse
import com.example.myapplication.models.LoginState
import com.example.myapplication.models.LoginStatus
import com.example.myapplication.utility.RetrofitInstance
import kotlinx.coroutines.launch

import androidx.compose.ui.platform.LocalContext


class LoginViewModel : ViewModel() {
    private val _loginState =
        mutableStateOf(LoginState(status = LoginStatus.Initial, response = null))
    val loginState: State<LoginState> = _loginState;
    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                _loginState.value = _loginState.value.copy(status = LoginStatus.Loading)

                val response = RetrofitInstance.getApi().login(
                    mapOf(
                        "username" to username,
                        "password" to password
                    )
                );
                println("Data is--${response}")
                _loginState.value =
                    _loginState.value.copy(status = LoginStatus.Success, response = response);

            } catch (e: Exception) {
                println("Data is--$e")
                _loginState.value = loginState.value.copy(status = LoginStatus.Failure)
            }
        }
    }
}