package com.example.kakaoimageai.presentation.view.fragment

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.viewModels
import com.example.kakaoimageai.MyApplication
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.FragmentPhotoViewBinding
import com.example.kakaoimageai.presentation.view.adapter.PhotoAdapter
import com.example.kakaoimageai.presentation.view.base.BaseFragment
import com.example.kakaoimageai.presentation.view.base.BindingFragment
import com.example.kakaoimageai.presentation.view.dialog.ProgressDialog
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import com.example.kakaoimageai.presentation.viewmodel.UserInfoViewModel
import com.example.kakaoimageai.utils.CommonUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_photo_view.*

@AndroidEntryPoint
class PhotoViewFragment : BindingFragment<FragmentPhotoViewBinding>(R.layout.fragment_photo_view) {

    private var userId = ""

    private val photoAdapter = PhotoAdapter(photoCallBack())

    override fun initView() {
        showProgress()
        binding.rcImgs.adapter = photoAdapter
        userInfoViewModel.setUserInfo()

        binding.swiper.setOnRefreshListener {
            binding.swiper.isRefreshing = false
        }
    }

    override fun initObserve() {
        photoViewModel.photoList.observe(this){
            photoAdapter.items = it
            dismissProgress()
        }
        userInfoViewModel.userId.observe(this){
            userId = it.toString()
            photoViewModel.getImagesFromDB(userId)
        }


    }

    private fun photoCallBack() = object : PhotoAdapter.PhotoCallback {

    }

}