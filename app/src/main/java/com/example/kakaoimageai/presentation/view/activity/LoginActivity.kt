package com.example.kakaoimageai.presentation.view.activity

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kakaoimageai.R
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private var btnLogin: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin?.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
                UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(
                    this@LoginActivity,
                    callback = callback
                )
            }
        }
    }
}
internal val callback : (OAuthToken?, Throwable?) -> Unit = { token, error ->
    if (error != null) {
        Timber.e("로그인 실패- $error")
    } else if (token != null) {
        UserApiClient.instance.me { user, error ->
            val kakaoId = user!!.id
            viewModel?.addKakaoUser(token.accessToken, kakaoId)
        }
        Timber.d("로그인성공 - 토큰 ${authManager.token}")
    }
}


class SocketApplication : Application() {
    companion object {
        var appContext : Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        appContext = this
        KakaoSdk.init(this,getString(R.string.kakao_app_key))
    }
}