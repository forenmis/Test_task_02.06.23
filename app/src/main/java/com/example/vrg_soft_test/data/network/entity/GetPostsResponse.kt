package com.example.vrg_soft_test.data.network.entity

import com.google.gson.annotations.SerializedName

data class GetPostsResponse(
    @SerializedName("data")
    val data: PostsData
)

data class PostsData(
    @SerializedName("children")
    val children: List<PostData>,
    @SerializedName("after")
    val after: String
)

data class PostData(
    @SerializedName("data")
    val data: NetworkPost
)