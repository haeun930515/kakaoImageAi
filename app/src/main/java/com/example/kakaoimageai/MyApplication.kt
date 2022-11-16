package com.example.kakaoimageai

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        private var instance: MyApplication? = null

        @JvmStatic
        val application: MyApplication
            get() {
                return instance!!.applicationContext as MyApplication
            }
    }

    init {
        instance = this
    }

}