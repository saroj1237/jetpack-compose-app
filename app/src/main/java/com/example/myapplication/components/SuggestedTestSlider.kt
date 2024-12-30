package com.example.myapplication.components

import SuggestedTest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun SuggestedTestSlider(suggestedTests: List<SuggestedTest>) {
    Column {
        Text(
            text = "Suggested Tests",
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        LazyRow(
            modifier = Modifier
                .padding(12.dp)
                .height(200.dp),
            contentPadding = PaddingValues(end = 12.dp),
        ) {
            items(suggestedTests.size) { index ->
                Card(
                    modifier = Modifier
                        .width(212.dp)
                        .padding(end = 12.dp)

                ) {
                    Column {
                        AsyncImage(
                            model = suggestedTests[index].image,
                            modifier = Modifier.weight(0.7f),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                        )
                        Text(
                            text = suggestedTests[index].title,
                            modifier = Modifier
                                .weight(0.3f)
                                .padding( 8.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

        }
    }
}