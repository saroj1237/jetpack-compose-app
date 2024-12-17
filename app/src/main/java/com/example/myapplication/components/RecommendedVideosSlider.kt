package com.example.myapplication.components

import SuggestedVideo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun RecommendedVideosSlider(suggestedVideo: List<SuggestedVideo>) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(220.dp)
//    ) {
    Column {
        Text(
            text = "Recommended Videos",
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        LazyRow(
            modifier = Modifier
                .padding(12.dp)
                .height(200.dp),
            contentPadding = PaddingValues(end = 12.dp),
        ) {
            items(suggestedVideo.size) { index ->
                Card(
                    modifier = Modifier
                        .width(212.dp)
                        .padding(end = 12.dp)

                ) {
                    Column {
                        AsyncImage(
                            model = suggestedVideo[index].thumbnail,
                            modifier = Modifier.weight(0.7f),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                        )
                        Text(
                            text = suggestedVideo[index].title,
                            modifier = Modifier
                                .weight(0.3f)
                                .padding(horizontal = 8.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            //        }
        }
    }
}

