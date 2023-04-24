package com.example.memes.data

/*
* @Date :Apr 04,2023
* @Created By : Bharat Tripathi
* @Description : create a data class which hold data.
* */
data class PostResponse(
    val author: String,
    val nsfw: Boolean,
    val postLink: String,
    val preview: List<String>,
    val spoiler: Boolean,
    val subreddit: String,
    val title: String,
    val ups: Int,
    val url: String
)