package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.utility.LocalStorage
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.models.LoginStatus
import com.example.myapplication.viewmodels.LoginViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val localStorage = LocalStorage(context)
    val loginState by loginViewModel.loginState;
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(loginState.status) {
        if (loginState.status == LoginStatus.Failure) {
            scope.launch {
                snackbarHostState.showSnackbar("Login Failed")
            }
        }
    }
    // Handle navigation for success
    LaunchedEffect(loginState.status) {
        if (loginState.status == LoginStatus.Success) {
            println("My Token is : ${loginState.response?.token}")
            loginState.response?.token?.let { token ->
                localStorage.saveToken(token)
            }
            navController.navigate("Landing") {
                popUpTo("Login") { inclusive = true }
            }
        }
    }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(

                text = "Hello, Welcome Back!",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Text(
                "We are excited to see you here.",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF737373),
                )
            )
            Box(modifier = Modifier.height(24.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Label") }
            )
            Box(modifier = Modifier.height(16.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Label") }
            )
            Box(modifier = Modifier.height(16.dp))
            if (loginState.status == LoginStatus.Loading) {
                CircularProgressIndicator()
            } else {
                ElevatedButton(
                    onClick = {
//                        scope.launch {
//                            snackbarHostState.showSnackbar(
//                                message = "Snack bar",
//                            )
//                        }
                        loginViewModel.login(username = email, password = password)
                    }
                ) {
                    Text("Login")
                }
            }

        }
    }
}