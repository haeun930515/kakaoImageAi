package com.example.kakaoimageai.presentation.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_photo_view.*

class PhotoViewFragment : Fragment() {
    fun initView(){
        //버튼 이벤트
        btnphoto.setOnClickListener {

            //갤러리 호출
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            activityResult.launch(intent)
        }
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
                .into(imageView) //보여줄 위치
        }
    }
}