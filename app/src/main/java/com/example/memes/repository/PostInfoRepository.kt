package com.example.memes.repository

import com.example.memes.network.api.ApiHelperImpl
import javax.inject.Inject
/*
* @Date : Apr 21,2023
* @Created By : Bharat Tripathi
* @Description: This class is repository class which get from api impl class.
* */
class PostInfoRepository @Inject constructor(private val apiHelperImpl: ApiHelperImpl) {
    suspend fun getPostInfoDetails() = apiHelperImpl.getPostInfo()
}