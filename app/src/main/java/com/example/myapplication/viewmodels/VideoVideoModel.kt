package com.example.myapplication.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Video
import com.example.myapplication.models.VideoResponse
import com.example.myapplication.utility.RetrofitInstance
import kotlinx.coroutines.launch

class VideoViewModel : ViewModel() {

    // Latest Lecture Videos
    private val _latestLectureVideosResponse = mutableStateOf<VideoResponse?>(null);
    val latestLectureVideos: State<List<Video>> =
        derivedStateOf { _latestLectureVideosResponse.value?.results ?: emptyList() };

    // Latest Recorded Videos
    private val _latestRecordedVideosResponse = mutableStateOf<VideoResponse?>(null);
    val latestRecordedVideos: State<List<Video>> =
        derivedStateOf { _latestRecordedVideosResponse.value?.results ?: emptyList() };

    // Latest QAD Videos
    private val _latestQADVideosResponse = mutableStateOf<VideoResponse?>(null);
    val latestQADVideos: State<List<Video>> =
        derivedStateOf { _latestQADVideosResponse.value?.results ?: emptyList() };


    fun fetchLatestVideos(category: String, limit: Int = 50) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getVideoApi()
                    .getLatestVideos(category, limit)
                println("Latest videos($category): $response")
                when (category) {
                    "Lecture video" -> {
                        _latestLectureVideosResponse.value = response
                    }

                    "Recorded video" -> {
                        _latestRecordedVideosResponse.value = response
                    }

                    "QAD" -> {
                        _latestQADVideosResponse.value = response
                    }

                    else -> {
                        println("Invalid category")
                    }
                }
            } catch (e: Exception) {
                println("Error fetching latest videos:$e")
            }
        }
    }
}