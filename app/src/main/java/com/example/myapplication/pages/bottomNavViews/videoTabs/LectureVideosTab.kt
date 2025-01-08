package com.example.myapplication.pages.bottomNavViews.videoTabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.models.Video
import com.example.myapplication.viewmodels.VideoViewModel
import com.example.myapplication.widgets.LatestVideosSlider

@Composable
fun LectureVideosTab(viewModel: VideoViewModel = viewModel()) {
    Column(modifier = Modifier.padding(12.dp)) {
        LatestVideosSlider(viewModel.latestLectureVideos.value)
    }
}