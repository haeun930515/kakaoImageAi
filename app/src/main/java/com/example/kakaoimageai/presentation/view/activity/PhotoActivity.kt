package com.example.kakaoimageai.presentation.view.activity

import androidx.activity.viewModels
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.ActivityPhotoBinding
import com.example.kakaoimageai.presentation.view.base.BaseActivity
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import com.example.kakaoimageai.utils.CommonUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoActivity : BaseActivity<ActivityPhotoBinding>(R.layout.activity_photo){

    private val photoViewModel : PhotoViewModel by viewModels()

    //PhotoCreateFragment 사용자 input
    private var userInput = ""


    //From PhotoCreateFragment 인지 From PhotoViewFragment 인지 Boolean 인자
    private var isFromCreate = false

    override fun initView() {
        super.initView()
        if(!intent.getStringExtra("UserInput").isNullOrEmpty()){
            userInput = intent.getStringExtra("UserInput").toString()
        }
        isFromCreate =  intent.getBooleanExtra("isFromCreate",false)

        binding.txtTestFromInput.text = userInput

        //TODO: "var userInput" 으로, ViewModel의 "getPhotoFromDallE" Function 을 연결 -> API 결과 반영

    }

    override fun initObserve() {

        //url로부터 이미지 로딩을 위한 Observer (Sample)
        photoViewModel.result.observe(this){
            CommonUtil.urlImgLoadGlide(it.data[0].url,binding.imgTest,this)
        }

    }

}