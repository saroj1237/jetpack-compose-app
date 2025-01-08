package com.example.myapplication.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.models.Video
import com.example.myapplication.viewmodels.VideoViewModel

@Composable
fun LatestVideosSlider(videos: List<Video>, viewModel: VideoViewModel = viewModel()) {

    Column {
        Text(
            "Latest videos",
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
        Box(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier.height(200.dp)
        ) {
            LazyRow {
                items(
                    videos.size
                ) {
                    Box(modifier = Modifier.padding(end = 10.dp)) {
                        VideoCard(
                            video = videos[it],
                            onClick = {}
                        )
                    }

                }
            }
        }


    }
}
