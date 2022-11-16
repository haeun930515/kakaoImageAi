package com.example.kakaoimageai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.kakaoimageai.databinding.ActivityPhotoViewBinding

class PhotoView : AppCompatActivity() {
    lateinit var binding: ActivityPhotoViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

            setContentView(binding.root)

            //버튼 이벤트
            binding.galleryBtn.setOnClickListener {

                //갤러리 호출
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                activityResult.launch(intent)
            }
        }//onCreate

        //결과 가져오기
        private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        //결과 코드 OK , 결가값 null 아니면
        if(it.resultCode == RESULT_OK && it.data != null){
            //값 담기
            val uri  = it.data!!.data
            //화면에 보여주기 load(uri) = 이미지, into = 보여줄 위치
            Glide.with(this).load(uri).into(binding.imageView)
        }
    }
}