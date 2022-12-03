package com.example.kakaoimageai.presentation.view.activity

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.ActivitySplashBinding
import com.example.kakaoimageai.presentation.view.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash){

    override fun initView() {
        Loadingstart()
    }

    fun Loadingstart(){
        // 일정 시간 지연 이후 실행하기 위한 코드
        Handler(Looper.getMainLooper()).postDelayed({

            // 일정 시간이 지나면 MainActivity로 이동
            val intent= Intent( this,MainActivity::class.java)
            startActivity(intent)

            // 이전 키를 눌렀을 때 스플래스 스크린 화면으로 이동을 방지하기 위해
            // 이동한 다음 사용안함으로 finish 처리
            finish()

        }, 2000) // 시간 2초 이후 실행

    }
}