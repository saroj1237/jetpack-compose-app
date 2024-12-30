package com.example.myapplication.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = "Dashboard"

            ),
            BottomNavigationItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = "Search"
            ),
            BottomNavigationItem(
                label = "Videos",
                icon = Icons.Filled.PlayArrow,
                route = "Videos"
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.AccountCircle,
                route = "Profile"
            ),
        )
    }
}