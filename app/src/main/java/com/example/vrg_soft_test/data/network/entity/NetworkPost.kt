package com.example.vrg_soft_test.data.network.entity

import com.example.vrg_soft_test.entity.Post
import com.google.gson.annotations.SerializedName

data class NetworkPost(
    @SerializedName("author_fullname")
    val authorName: String,
    @SerializedName("created")
    val created: Long,
    @SerializedName("thumbnail")
    val image: String?,
    @SerializedName("num_comments")
    val numComments: Long,
    @SerializedName("title")
    val title: String
)

fun NetworkPost.toPost(): Post {
    return Post(authorName, created * 1_000L, image, numComments, title)
}
