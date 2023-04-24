package com.example.memes.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
* @Date :Apr 04,2023
* @Created By : Bharat Tripathi
* @Description : create a base application class which provide container to use HiltAppApplication
* */
@HiltAndroidApp
class BaseApplication : Application() {

}