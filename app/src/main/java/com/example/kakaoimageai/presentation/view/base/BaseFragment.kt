package com.example.kakaoimageai.presentation.view.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kakaoimageai.presentation.view.dialog.ProgressDialog
import com.example.kakaoimageai.presentation.viewmodel.MainViewModel
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import com.example.kakaoimageai.presentation.viewmodel.UserInfoViewModel
import dagger.hilt.android.internal.managers.FragmentComponentManager

open class BaseFragment : Fragment(){


    private lateinit var progressDialog: ProgressDialog
    protected lateinit var baseActivity: BaseActivity


    protected val photoViewModel: PhotoViewModel by viewModels()
    protected val mainViewModel: MainViewModel by viewModels()
    protected val userInfoViewModel: UserInfoViewModel by viewModels()

    protected open fun initView(){}
    protected open fun initObserve(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = FragmentComponentManager.findActivity(context) as BaseActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(baseActivity)
        initView()
        initObserve()
    }

    protected fun showProgress() {
        progressDialog.show()
    }

    protected fun dismissProgress() {
        progressDialog.dismiss()
    }


}