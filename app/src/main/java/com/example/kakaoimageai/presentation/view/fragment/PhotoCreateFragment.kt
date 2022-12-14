package com.example.kakaoimageai.presentation.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.FragmentPhotoCreateBinding
import com.example.kakaoimageai.domain.entity.firebaseUser
import com.example.kakaoimageai.presentation.view.activity.PhotoActivity
import com.example.kakaoimageai.presentation.view.base.BaseFragment
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import com.example.kakaoimageai.presentation.viewmodel.UserInfoViewModel
import com.example.kakaoimageai.utils.CommonUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_photo_create.*

@AndroidEntryPoint
class PhotoCreateFragment :
    BaseFragment<FragmentPhotoCreateBinding>(R.layout.fragment_photo_create) {


    override fun initView() {
        super.initView()


        /**
         * photo create fragment - create button
         *
         * intent에 필요한 정보 저장후 -> Photo activity 전달
         *
         */
        binding.createBtn.setOnClickListener {
            val userInputTxt = binding.txtSearchImg.text.toString()
            val intent = Intent(context,PhotoActivity::class.java)
            intent.putExtra("UserInput",userInputTxt)
            intent.putExtra("isFromCreate",true)
            startActivity(intent)
        }
    }


}