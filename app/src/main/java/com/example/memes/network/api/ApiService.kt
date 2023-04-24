package com.example.memes.network.api

import com.example.memes.data.PostResponse
import retrofit2.Response
import retrofit2.http.GET

/*
* @Date : Apr 21,2023
* @Created By : Bharat Tripathi
* @Description: This interface create for get post data info.
* */
interface ApiService {
    @GET("gimme")
    suspend fun getPostInfo(): Response<PostResponse>
}