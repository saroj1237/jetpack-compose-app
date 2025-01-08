package com.example.myapplication.pages.bottomNavViews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.pages.bottomNavViews.videoTabs.LectureVideosTab
import com.example.myapplication.pages.bottomNavViews.videoTabs.QadVideosTab
import com.example.myapplication.pages.bottomNavViews.videoTabs.RecordedVideosTab
import com.example.myapplication.viewmodels.VideoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Videos(viewModel: VideoViewModel = viewModel()) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Lecture videos", "Recorded videos", "QAD")

    LaunchedEffect(Unit) {
        viewModel.fetchLatestVideos("Lecture video")
        viewModel.fetchLatestVideos("Recorded video")
        viewModel.fetchLatestVideos("QAD")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Videos") },

                actions = {
                    Image(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .clickable { },
                        painter = painterResource(R.drawable.baseline_bookmark_24),
                        contentDescription = "Bookmarks",
                    )
                }


            )
        },

        ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                    )
                }
            }
            when (tabIndex) {
                0 -> LectureVideosTab()
                1 -> RecordedVideosTab()
                2 -> QadVideosTab()
            }
        }

    }
}