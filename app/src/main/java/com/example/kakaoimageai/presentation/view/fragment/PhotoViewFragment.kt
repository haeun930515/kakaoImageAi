package com.example.kakaoimageai.presentation.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.FragmentPhotoViewBinding
import com.example.kakaoimageai.presentation.view.adapter.PhotoAdapter
import com.example.kakaoimageai.presentation.view.base.BaseFragment
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import com.example.kakaoimageai.presentation.viewmodel.UserInfoViewModel
import kotlinx.android.synthetic.main.fragment_photo_view.*

class PhotoViewFragment : BaseFragment<FragmentPhotoViewBinding>(R.layout.fragment_photo_view) {

    private val UserInfoViewModel : UserInfoViewModel by viewModels()

    private val photoAdapter = PhotoAdapter(photoCallBack())
    override fun initView() {
        binding.rcImgs.adapter = photoAdapter

    }

    //TODO: 뷰모델에서 Photo List Observe 필요
    override fun initObserve() {

    }

    //TODO: RecyclerView 내부 아이템 클릭시, 콜백 함수
    private fun photoCallBack() = object : PhotoAdapter.PhotoCallback {

    }

    //TODO: 데이터베이스에서 사용자 토큰에 따른 리스트 목록 가져오는 함수
    //
    private fun getPhotoList(dataInitialize: Boolean){

    }
}