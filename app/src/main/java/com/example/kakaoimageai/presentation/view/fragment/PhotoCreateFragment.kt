package com.example.kakaoimageai.presentation.view.fragment

import android.content.Intent
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.FragmentPhotoCreateBinding
import com.example.kakaoimageai.presentation.view.activity.PhotoActivity
import com.example.kakaoimageai.presentation.view.base.BaseFragment
import com.example.kakaoimageai.presentation.view.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoCreateFragment :
    BindingFragment<FragmentPhotoCreateBinding>(R.layout.fragment_photo_create) {


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