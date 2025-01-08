package com.example.myapplication.models

data class VideoResponse(
    val count: Int,
    val results: List<Video>
)

data class Video(
    val id: Int,
    val is_accessible: Boolean,
    val is_bookmarked: Boolean,
    val apsara_id: String?,
    val category: String,
    val title: String,
    val description: String,
    val video_type: String,
    val video_id: String,
    val thumbnail: String?,
    val is_free: Boolean
)