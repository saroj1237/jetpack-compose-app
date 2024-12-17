package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    val id: Int,
    val position: String,
    val banner: String,
    val link: String?,
    val priority: Int,
    @SerializedName("expiry_datetime") val expiryDatetime: String?,
    @SerializedName("created_datetime") val createdDatetime: String
)
