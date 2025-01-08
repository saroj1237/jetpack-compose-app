package com.example.myapplication.widgets

import SuggestedNote
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
fun SuggestedNoteSlider(suggestedNotes: List<SuggestedNote>) {
    Column {
        Text(
            text = "Suggested Notes",
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        LazyRow(
            modifier = Modifier
                .padding(12.dp)
                .height(200.dp),
            contentPadding = PaddingValues(end = 12.dp),
        ) {
            items(suggestedNotes.size) { index ->
                Card(
                    modifier = Modifier
                        .width(212.dp)
                        .padding(end = 12.dp)

                ) {
                    Column {
                        AsyncImage(
                            model = suggestedNotes[index].image,
                            modifier = Modifier.weight(0.7f),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                        )
                        Text(
                            text = suggestedNotes[index].description,
                            modifier = Modifier
                                .weight(0.3f)
                                .padding(8.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

        }
    }
}