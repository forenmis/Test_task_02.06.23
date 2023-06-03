package com.example.vrg_soft_test.data.network

import com.example.vrg_soft_test.data.network.entity.GetPostsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("top.json")
    suspend fun getPosts(@Query("after") after: String?): GetPostsResponse
}