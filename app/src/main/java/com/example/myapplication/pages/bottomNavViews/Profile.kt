package com.example.myapplication.pages.bottomNavViews

import androidx.compose.material.icons.Icons
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.myapplication.viewmodels.ProfileViewModel
import kotlinx.coroutines.launch


@Composable
fun Profile(navController: NavHostController, profileViewModel: ProfileViewModel = viewModel()) {
    LaunchedEffect(Unit) {

        profileViewModel.getProfile()

    }
    val profile = remember { profileViewModel.profile }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RectangleShape)                       // clip to the circle shape
                            .border(2.dp, Color.Gray, RectangleShape),
                        model = profile.value?.profile_pic
                            ?: "https://plus.unsplash.com/premium_photo-1688350808212-4e6908a03925?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8cHJvZmlsZSUyMHBpY3xlbnwwfHwwfHx8MA%3D%3D",
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )
                    Box(modifier = Modifier.width(12.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = profile.value?.full_name ?: "",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            profile.value?.email ?: ""

                        )
                    }
                }
            }
        }
        item {
            ListItem(
                modifier = Modifier.clickable {
//                    navController.navigate("AboutUs")
                },
                leadingContent = { Icon(Icons.Outlined.Info, null) },
                headlineContent = { Text("About Us") }
            )
        }
        item {
            ListItem(
                modifier = Modifier.clickable {

                },
                leadingContent = { Icon(Icons.AutoMirrored.Outlined.ExitToApp, null) },
                headlineContent = { Text("Logout") }
            )
        }
    }

}