package com.example.myapplication.viewmodels

import HomeContent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.BannerResponse
import com.example.myapplication.models.PostModel
import com.example.myapplication.utility.RetrofitInstance
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _topBanners = mutableStateOf<List<BannerResponse>>(emptyList())
    private val _homeContent = mutableStateOf<HomeContent?>(null)

    val topBanners: State<List<BannerResponse>> = _topBanners
    val homeContent: State<HomeContent?> = _homeContent

    fun fetchBanners() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getApi().getBanners()
                println("Fetched Banners: $response")
                val topBanners = mutableListOf<BannerResponse>()
                response.forEach { item ->
                    if (item.position == "Position 1") topBanners.add(item);
                }
                println("Filtered Banners: $topBanners");
                _topBanners.value = topBanners
            } catch (e: Exception) {
                println("Error fetching banner: ${e.message}")
            }
        }
    }

    fun fetchHomeContent() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getApi().getHomeContent()
                println("Fetched home conent: $response")
                _homeContent.value = response
            } catch (e: Exception) {
                println("Error fetching home contents: ${e.message}")
            }
        }
    }
}