package com.example.vrg_soft_test.data.network

import com.example.vrg_soft_test.data.network.entity.toPost
import com.example.vrg_soft_test.entity.PostPage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {

    private val api: Api
    private val gson: Gson

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        gson = GsonBuilder().serializeNulls().create()
        val retrofit = Retrofit.Builder().baseUrl("https://www.reddit.com/").client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(Api::class.java)
    }

    suspend fun loadPosts(after: String?): PostPage {
        val response = api.getPosts(after)
        return PostPage(
            response.data.children.map { it.data.toPost() },
            response.data.after
        )
    }
}