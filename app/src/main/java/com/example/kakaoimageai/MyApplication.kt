package com.example.kakaoimageai

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
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

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this,"355c63a4e90ec5ea81c61e050a4b88b4")
    }

}