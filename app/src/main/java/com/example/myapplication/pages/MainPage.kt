package com.example.myapplication.pages

import LandingPage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.models.BottomNavigationItem
import com.example.myapplication.utility.LocalStorage

import androidx.compose.ui.platform.LocalContext


@Composable
fun MainPage(navController: NavHostController) {
    val localStorage = LocalStorage(LocalContext.current)
    val token = localStorage.getToken();
    NavHost(navController, startDestination = if (token == null) "Login" else "Landing") {
        composable("Login") { LoginPage(navController) }
        composable("Landing") { LandingPage(navController) }
        composable("notification") { Text("Notification")  }
    }
}

