package com.example.memes.network.api

import com.example.memes.data.PostResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getPostInfo() : Response<PostResponse>
}