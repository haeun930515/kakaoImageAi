package com.example.kakaoimageai.presentation.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.kakaoimageai.domain.entity.firebaseUser
import com.example.kakaoimageai.utils.CommonUtil
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

abstract class BaseActivity<VB: ViewDataBinding>(@LayoutRes private val resId: Int) : AppCompatActivity() {

    lateinit var binding: VB

    val database = Firebase.database(CommonUtil.FB_DB_URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,resId)
        initView()
        initObserve()
    }

    protected open fun initView(){}
    protected open fun initObserve(){}

    /**
     * parameter : String , String
     *
     * Class : firebaseUser.kt
     *          var name : String,
     *          var token : String
     *
     * = 저장 루트
     * DBreference
     *      - users
     *          - "token" (@userId)
     *              - name : @userName
     *              - token : @userId
     *
     */

    fun addUserToDB(userName: String, userId: String){
        val user = firebaseUser(userName, userId)
        CommonUtil.FB_REF = database.getReference(CommonUtil.FB_DB_USERS).child(userId)
        CommonUtil.FB_REF.setValue(user)
    }
}