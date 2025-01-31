package com.example.myapplication.pages.bottomNavViews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.widgets.RecommendedVideosSlider
import com.example.myapplication.widgets.SuggestedNoteSlider
import com.example.myapplication.widgets.SuggestedTestSlider
import com.example.myapplication.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(viewModel: HomeViewModel = viewModel(),
         parentNavController: NavHostController) {
    LaunchedEffect(Unit) {
        viewModel.fetchBanners();
        viewModel.fetchHomeContent();
    }
    val state = rememberPagerState(pageCount = { viewModel.topBanners.value.size })
    val homeContent = remember { viewModel.homeContent }
    val topBanners = remember { viewModel.topBanners }


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 12.dp),
                title = { Text("Welcome") },
                navigationIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)                       // clip to the circle shape
                            .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
                    )
                },
                actions = {
                    Row {
                        IconButton(onClick = {
parentNavController.navigate("notification");
                        },
                            content = {Icon(Icons.Outlined.Notifications, null)
                        },
                            )
                    }
                })
        },
    ) { pa ->

        LazyColumn(
            modifier = Modifier.padding(pa)

        ) {
            item {
                Box(
                    modifier = Modifier
                        .background(Color.Gray)
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        state = state
                    ) { index ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            model = topBanners.value[index].banner,
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                        )
                    }

                }
            }
            item {
                Box(
                    modifier = Modifier

                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        homeContent.value?.suggested_questions?.first()
                            ?.let {
                                Text(
                                    text = it.question, style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                            }
                        Box(modifier = Modifier.height(16.dp))
                        homeContent.value?.suggested_questions?.first()?.let {
                            homeContent.value?.suggested_questions?.first()?.options?.map { i ->
                                Column {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                if (!it.is_attempted)
                                                    viewModel.chooseOption(
                                                        optionId = i.id,
                                                        questionId = it.id
                                                    )
                                            },
                                        border = BorderStroke(
                                            1.dp,
                                            when {
                                                it.is_attempted && i.selected && i.is_correct_answer -> Color.Green
                                                it.is_attempted && i.selected && !i.is_correct_answer -> Color.Red
                                                it.is_attempted && !i.selected && i.is_correct_answer -> Color.Green
                                                else -> Color.Black
                                            }

                                        ),
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(12.dp),
                                            text = i.option

                                        )
                                    }
                                    Box(modifier = Modifier.height(12.dp))
                                }
                            }
                                ?.toList()
                        }

                    }
                }
            }

            item {
                homeContent.value?.let { RecommendedVideosSlider(it.suggested_video) }
            }

            item {
                homeContent.value?.let { SuggestedTestSlider(it.suggested_test) }
            }
            item {
                homeContent.value?.let {
                    SuggestedNoteSlider(it.suggested_note)
                }
            }
        }
    }

}