package com.example.vrg_soft_test.entity

data class PostPage(
    val posts: List<Post>,
    val after: String?
)
