package com.example.vrg_soft_test.entity

data class Post(
    val authorName: String,
    val created: Long,
    val image: String?,
    val numComments: Long,
    val title: String
)
