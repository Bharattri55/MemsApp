package com.example.memes.network.api

import com.example.memes.data.PostResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/*
* @Date : Apr 21,2023
* @Created By : Bharat Tripathi
* @Description: This impl class which implement method of api service.
* */

@Singleton
class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {
    override suspend fun getPostInfo(): Response<PostResponse> = apiService.getPostInfo()
}

/*
* This class is used to create module for api implementation
* */
@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiHelperModule{
    @Singleton
    @Binds
    abstract fun bindApiHelper(apiHelperImpl: ApiHelperImpl) : ApiHelper
}