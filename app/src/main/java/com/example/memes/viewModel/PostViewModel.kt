package com.example.memes.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memes.data.PostResponse
import com.example.memes.di.NetworkHelper
import com.example.memes.repository.PostInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*

/*
* @Date : Apr 21,2023
* @Created By : Bharat Tripathi
* @Description: This class is view-model class which get from repository class to show data on UI.
* */
@HiltViewModel
class PostViewModel @Inject constructor(
    private val postInfoRepository: PostInfoRepository, private val networkHelper: NetworkHelper
) : ViewModel() {

    var mutableList = mutableStateListOf<ArrayList<PostResponse>>()
    var isRefreshing by mutableStateOf(false)

    init {
        fetchPostInfoDetails()
    }

    fun fetchPostInfoDetails() {
        if (networkHelper.isNetworkConnected()) {
            isRefreshing = true
            viewModelScope.launch {
                postInfoRepository.getPostInfoDetails().let {
                    if (it.isSuccessful) {
                        val postResponse = it.body() as PostResponse
                        mutableList.add(0, arrayListOf(postResponse))
                        isRefreshing = false
                    }
                }
            }
        }
    }
}