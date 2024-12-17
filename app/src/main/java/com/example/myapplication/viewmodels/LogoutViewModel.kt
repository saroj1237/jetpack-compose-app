package com.example.myapplication.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.utility.RetrofitInstance
import kotlinx.coroutines.launch

enum class LogoutState { Initial, Loading, Success, Failure }

class LogoutViewModel : ViewModel() {
    private val _logoutState = mutableStateOf<LogoutState>(LogoutState.Initial)

    val logoutState: State<LogoutState> = _logoutState

    fun logout() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getApi().logout()
                println("Logout response: $response")
                if (response is Map<*, *>) {
                    if (response["success"] == true) {
                        println("Logout response: ${response["success"]}")
                        _logoutState.value = LogoutState.Success
                    } else {
                        _logoutState.value = LogoutState.Failure
                    }
                } else {
                    _logoutState.value = LogoutState.Failure
                }

            } catch (e: Exception) {
                println("Logout exception: $e")
                _logoutState.value = LogoutState.Failure
            }
        }
    }
}