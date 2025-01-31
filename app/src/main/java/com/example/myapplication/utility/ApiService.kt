package com.example.myapplication.utility

import HomeContent
import android.content.Context
import androidx.compose.ui.graphics.rememberGraphicsLayer
import com.example.myapplication.models.BannerResponse
import com.example.myapplication.models.LoginResponse
import com.example.myapplication.models.PostModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService{
    @POST("api/login/")
    suspend fun login(@Body body: Map<String, String>):LoginResponse

    @GET("api/banner/")
    suspend fun getBanners():List<BannerResponse>

    @GET("api/v2/home_page_data/")
    suspend fun getHomeContent():HomeContent

    @GET("api/account-details/")
    suspend fun getProfile():LoginResponse

    @GET("api/v2/logout/")
    suspend fun logout():Any
}


class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val localStorage = LocalStorage(context)
        val token = localStorage.getToken()

        val request = chain.request().newBuilder().apply {
            if (!token.isNullOrEmpty()) {
                addHeader("Authorization", "Token $token")
            }
        }.build()

        return chain.proceed(request)
    }

}

class SessionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code == 401) {
            val localStorage = LocalStorage(context)
            localStorage.deleteToken()
            // Navigate to Login Page.

        }

        return response
    }
}

object RetrofitInstance{
private const val BASE_URL = "https://liveguru.net/"
//    private const val BASE_URL = "https://staging.liveguru.susankya.com.np/"

    private var apiService: ApiService? = null
    private var videoApiService: VideoApiService? = null
    private var notificationApiService:NotificationApiService?=null

    fun initialize(context: Context) {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
        videoApiService =retrofit.create(VideoApiService::class.java)
        notificationApiService = retrofit.create(NotificationApiService::class.java)
    }

    fun getApi(): ApiService {
        return apiService ?: throw IllegalStateException("RetrofitInstance is not initialized. Call initialize(context) first.")
    }
    fun getVideoApi():VideoApiService{
        return videoApiService ?: throw IllegalStateException("RetrofitInstance is not initialized. Call initialize(context) first.")
    }

    fun getNotificationApi():NotificationApiService{
        return notificationApiService?:throw  IllegalStateException("RetrofitInstance is not initialized. Call initialize(context) first.")
    }
}
