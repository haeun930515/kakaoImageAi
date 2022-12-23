package com.example.kakaoimageai.presentation.view.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.ActivityPhotoBinding
import com.example.kakaoimageai.presentation.view.base.BaseActivity
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import com.example.kakaoimageai.utils.CommonUtil
import com.example.kakaoimageai.utils.JavaUtils
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayInputStream

@AndroidEntryPoint
class PhotoActivity : BaseActivity<ActivityPhotoBinding>(R.layout.activity_photo){

    private val photoViewModel : PhotoViewModel by viewModels()

    //PhotoCreateFragment 사용자 input
    private var userInput = ""

    //URL
    private var photoURL = ""

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
        photoViewModel.getPhotoFromDallE(userInput)


        if(isFromCreate){ // PhotoCreateFragment 라면 저장버튼 생성
            binding.saveBtn.visibility = View.VISIBLE
        } else { // PhotoCreateFragment 아니라면 저장버튼 안보이게
            binding.saveBtn.visibility = View.GONE
        }



        //TODO: 각 저장(어플인지, 핸드폰 내장인지), 삭제(확인 알람), 공유(sns, 어플 피드), 닫기("isFromCreate"로 넘기는지, 액티비티 위에 있다면 해당 엑티비티만 닫기, 방금만들었다면 이미지를 삭제할 것인지) 버튼 구현
        binding.saveBtn.setOnClickListener { // 저장 버튼
            Log.d("PHOTOURL",photoURL)
//            test = CommonUtil.imageToByte(photoURL,this)
        }
        binding.deleteBtn.setOnClickListener { // 삭제 버튼
            // 다이얼로그를 생성하기 위해 Builder 클래스 생성자를 이용
            val builder = AlertDialog.Builder(this)
            builder.setTitle("경고, 이미지가 삭제됩니다.")
                .setMessage("정말 삭제 하시겠습니까?")
                .setPositiveButton("삭제",
                    DialogInterface.OnClickListener { dialog, id ->
                        // 이미지 삭제
                        if(isFromCreate){ // PhotoCreateFragment 라면
                            finish()
                        } else { // PhotoCreateFragment 아니라면

                            // 해당 파이어베이스 데이터베이스 값을 찾아서 삭제
                        }
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                        // 알람창만 닫기
                    })
            // 다이얼로그를 띄워주기
            builder.show()
        }
        binding.shareBtn.setOnClickListener { // 공유 버튼
//            CommonUtil.loadImgFromGlide(test,this,binding.fromBinary)
        }
        binding.closeBtn.setOnClickListener { // 닫기 버튼
            finish()
            if(!isFromCreate){
                finish()
            } else { // 크리에이트 액티비티라면 한번 더 물어보기
                // 다이얼로그를 생성하기 위해 Builder 클래스 생성자를 이용
                val builder = AlertDialog.Builder(this)
                builder.setTitle("경고, 이미지가 삭제됩니다.")
                    .setMessage("정말 삭제 하시겠습니까?")
                    .setPositiveButton("삭제",
                        DialogInterface.OnClickListener { dialog, id ->
                            // 이미지 삭제
                            finish()
                        })
                    .setNegativeButton("취소",
                        DialogInterface.OnClickListener { dialog, id ->
                            // 알람창만 닫기
                        })
                // 다이얼로그를 띄워주기
                builder.show()
            }
        }
    }

    override fun initObserve() {

        //url로부터 이미지 로딩을 위한 Observer (Sample)
        photoViewModel.result.observe(this){
            CommonUtil.urlImgLoadGlide(it.data[0].url,binding.imgTest,this)
            photoURL = it.data[0].url
        }

    }


    private fun getPhotoActivityImage(str: String){
        photoViewModel.getPhotoFromDallE(str)
    }

}