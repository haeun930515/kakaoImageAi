package com.example.kakaoimageai.presentation.view.base

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kakaoimageai.presentation.view.dialog.ProgressDialog
import com.example.kakaoimageai.presentation.viewmodel.*

open class BaseActivity: AppCompatActivity() {


    private var progressDialog: ProgressDialog? = null

    protected val photoViewModel: PhotoViewModel by viewModels()
    protected val mainViewModel: MainViewModel by viewModels()
    protected val userInfoViewModel: UserInfoViewModel by viewModels()

    protected fun showProgress() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.show()
    }

    protected fun dismissProgress() {
        progressDialog?.dismiss()
    }
}