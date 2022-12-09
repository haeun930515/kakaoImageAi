package com.example.kakaoimageai.presentation.view.activity

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import com.bumptech.glide.Glide
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.ActivityPhotoBinding
import com.example.kakaoimageai.presentation.view.base.BaseActivity
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import kotlinx.android.synthetic.main.activity_photo.view.*

class PhotoActivity : BaseActivity<ActivityPhotoBinding>(R.layout.activity_photo){

    private val photoViewModel : PhotoViewModel by viewModels()
    override fun initView() {

        //조건부 렌더링 (intent) 에 따른 뷰 구분
        //활동에 따른 이미지 뷰
        //타입에 따라 다르게 선언
        //intent.type = "" 
        activityResult.launch(intent)
        //크리에이트 뷰 에서 넘어온 경우
        //텍스트 -> 사진 출력, 저장 사진 -> 이미지 텍스트 리스트로 보내기, 공유, 삭제, 닫기

        //리스트 뷰 에서 넘어온 경우
        //이미지 텍스트 -> 사진 출력, 공유, 삭제, 닫기
        binding.saveBtn.isInvisible = true

    }
    //결과 가져오기
    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        //결과 코드 OK , 결가값 null 아니면
        if(it.resultCode == RESULT_OK && it.data != null){
            //값 담기
            val uri  = it.data!!.data

            //화면에 보여주기
            Glide.with(this)
                .load(uri) //이미지
                .into(binding.imageView) //보여줄 위치
        }
    }
}