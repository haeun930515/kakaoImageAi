package com.example.kakaoimageai.presentation.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.FragmentPhotoViewBinding
import com.example.kakaoimageai.presentation.view.base.BaseFragment
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import com.example.kakaoimageai.presentation.viewmodel.UserInfoViewModel
import kotlinx.android.synthetic.main.fragment_photo_view.*

class PhotoViewFragment : BaseFragment<FragmentPhotoViewBinding>(R.layout.fragment_photo_view) {

    private val UserInfoViewModel : UserInfoViewModel by viewModels()

    override fun initView() {
        binding.btnPhotoView.setOnClickListener{
            UserInfoViewModel.CheckTokenValid()
        }
    }

    override fun initObserve() {
        UserInfoViewModel.kakaoToken.observe(viewLifecycleOwner){
            binding.txtToken.text = it
        }
    }
}