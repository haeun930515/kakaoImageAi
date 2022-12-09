package com.example.kakaoimageai.presentation.view.activity

import androidx.activity.viewModels
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.ActivityPhotoBinding
import com.example.kakaoimageai.presentation.view.base.BaseActivity
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import kotlinx.android.synthetic.main.activity_photo.view.*

class PhotoActivity : BaseActivity<ActivityPhotoBinding>(R.layout.activity_photo){

    private val photoViewModel : PhotoViewModel by viewModels()
    override fun initView() {

        //조건부 렌더링 (intent) 에 따른 뷰 구분

        //크리에이트 뷰 에서 넘어온 경우
        //텍스트 -> 사진 출력, 저장 사진 -> 이미지 텍스트 리스트로 보내기, 공유, 삭제, 닫기
        byte[] byteArray  getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        binding.imageView.setImageBitmap(bitmap);

        //리스트 뷰 에서 넘어온 경우
        //이미지 텍스트 -> 사진 출력, 공유, 삭제, 닫기
        byte[] byteArray  getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        binding.imageView.setImageBitmap(bitmap);
    }
}