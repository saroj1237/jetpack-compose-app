package com.example.myapplication.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.LoginResponse
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.utility.RetrofitInstance
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _profile = mutableStateOf<LoginResponse?>(null)
    val profile: State<LoginResponse?> = _profile

    fun getProfile() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getApi().getProfile()
                _profile.value = response
            } catch (e: Exception) {
                println("Error")
            }


        }
    }
}