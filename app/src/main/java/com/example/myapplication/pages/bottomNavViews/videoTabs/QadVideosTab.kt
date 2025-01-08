package com.example.myapplication.pages.bottomNavViews.videoTabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodels.VideoViewModel
import com.example.myapplication.widgets.LatestVideosSlider

@Composable
fun QadVideosTab(viewModel: VideoViewModel= viewModel()) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        LatestVideosSlider(viewModel.latestQADVideos.value)
    }
}