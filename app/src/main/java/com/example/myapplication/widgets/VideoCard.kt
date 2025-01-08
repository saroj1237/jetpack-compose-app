package com.example.myapplication.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.myapplication.R
import com.example.myapplication.models.Video

@Composable
fun VideoCard(video: Video, onClick: () -> Unit) {
    Card(onClick = onClick) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .width(200.dp)
                    .weight(0.7F),
                model = video.thumbnail ?: "",
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                error = painterResource(R.drawable.baseline_info_outline_24)
            )
            Text(
                modifier = Modifier
                    .weight(0.3F)

                    .width(200.dp)
                    .padding(8.dp),
                text = video.title ?: "",
                maxLines = 2,
                )

        }
    }
}