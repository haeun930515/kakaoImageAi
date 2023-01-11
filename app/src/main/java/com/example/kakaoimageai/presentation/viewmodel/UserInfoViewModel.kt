package com.example.kakaoimageai.presentation.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kakaoimageai.domain.entity.FirebaseUser
import com.example.kakaoimageai.utils.CommonUtil
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(

) : ViewModel() {

    var isValidToken = MutableLiveData<Boolean>()
    var kakaoToken = MutableLiveData<String>()
    var userName = MutableLiveData<String?>()
    var userId = MutableLiveData<Long?>()

    init {
        checkTokenValid()
        setUserInfo()
    }

    fun checkTokenValid() {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        //로그인 필요
                        isValidToken.value = false
                    } else {
                        //기타 에러
                    }
                } else {
                    //토큰 유효성 체크 성공
                    UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                        if (error != null) {
                            Log.e(TAG, "토큰 정보 보기 실패", error)
                        } else if (tokenInfo != null) {
                            kakaoToken.value = tokenInfo.id.toString()
                        }
                    }

                    //사용자 정보
                    setUserInfo()

                }
            }
        } else {
            //로그인 필요
            //로그인 화면으로 이동 필요
        }
    }
    fun setUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                userName.value = user.kakaoAccount?.profile?.nickname
                userId.value = user.id
            }

        }
    }


    /**
     * addUserToDB - 사용자를 파이어베이스 DB에 저장
     *
     * parameter : username(String) , userId(String)
     *
     * Class : firebaseUser.kt
     *          var name : String,
     *          var list : MutableList<String>
     *
     * = 저장 루트
     * DBreference
     *      - users
     *          - "token" (@userId)
     *              - name : @userName
     *              - list : @empty list at first
     *
     */

    fun addUserToDB(userName: String, userId: String) {
        val user = FirebaseUser(userName, mutableListOf())
        CommonUtil.FB_REF = CommonUtil.database.getReference(CommonUtil.FB_DB_USERS).child(userId)
        CommonUtil.FB_REF.setValue(user)
    }
}