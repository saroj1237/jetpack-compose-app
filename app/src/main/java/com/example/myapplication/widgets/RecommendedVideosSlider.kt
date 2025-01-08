package com.example.myapplication.widgets

import SuggestedVideo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun RecommendedVideosSlider(suggestedVideo: List<SuggestedVideo>) {

    val showBottomSheet = remember { mutableStateOf(false) }

    SubscriptionBottomSheet(
        openSheet = showBottomSheet
    )
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
                        .clickable {
                            if (!suggestedVideo[index].is_free) showBottomSheet.value = true
                        }

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

