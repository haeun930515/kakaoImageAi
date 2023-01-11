package com.example.kakaoimageai.presentation.view.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.ActivityPhotoBinding
import com.example.kakaoimageai.presentation.view.base.BaseActivity
import com.example.kakaoimageai.presentation.view.base.BindingActivity
import com.example.kakaoimageai.presentation.view.dialog.ProgressDialog
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import com.example.kakaoimageai.presentation.viewmodel.UserInfoViewModel
import com.example.kakaoimageai.utils.CommonUtil
import com.example.kakaoimageai.utils.JavaUtils
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class PhotoActivity : BindingActivity<ActivityPhotoBinding>(R.layout.activity_photo){


    //PhotoCreateFragment 사용자 input
    private var userInput = ""

    //이미지 관련 var
    private var photoURL = ""
    private var binaryStr = ""


    //User Setting
    private var userId = ""
    private var userName = ""
    private var userToken = ""

    //From PhotoCreateFragment 인지 From PhotoViewFragment 인지 Boolean 인자
    private var isFromCreate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }


    private fun init() {

        if(!intent.getStringExtra("UserInput").isNullOrEmpty()){
            userInput = intent.getStringExtra("UserInput").toString()
        }
        isFromCreate =  intent.getBooleanExtra("isFromCreate",false)

        binding.txtTestFromInput.text = userInput


        /**
         * 각 viewmodel 필요 데이터 세팅
         *
         */
        userInfoViewModel.setUserInfo()
        photoViewModel.getPhotoFromDallE(userInput)


        if(isFromCreate){ // PhotoCreateFragment 라면 저장버튼 생성
            binding.saveBtn.visibility = View.VISIBLE
        } else { // PhotoCreateFragment 아니라면 저장버튼 안보이게
            binding.saveBtn.visibility = View.GONE
        }


        binding.saveBtn.setOnClickListener { // 저장 버튼

            Log.d("USERID",userId)
            Log.d("binary",binaryStr)
            if(!userId.isNullOrEmpty() && !binaryStr.isNullOrEmpty()){

                photoViewModel.addImageDB(userId,userInput,binaryStr)
            }
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

                        } else { // PhotoCreateFragment 아니라면

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
            photoViewModel.getImagesFromDB(userId)
        }
        binding.closeBtn.setOnClickListener { // 닫기 버튼
            finish()
        }


        // Observe : photoViewModel 의 API Image 응답 class
        photoViewModel.result.observe(this){
            CommonUtil.urlImgLoadGlide(it.data[0].url,binding.imgMain)
            imageToBinary(it.data[0].url)
        }
        userInfoViewModel.userId.observe(this){
            userId = it.toString()
        }
        userInfoViewModel.userName.observe(this){
            userName = it.toString()
        }
    }

    /**
     * imageToByte
     *      = url을 통해 생성된 이미지를 Binary String으로 변환
     *
     *      parameter :
     *          - url - "string" 이미지를 가져올 url
     *          - context - "context" 앱의 context
     *
     *      return : "String" 생성된 Binary String
     */


    private fun imageToBinary(url: String){
        Glide.with(CommonUtil.context)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val baos = ByteArrayOutputStream()
                    resource.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val bytes = baos.toByteArray()
                    binaryStr = JavaUtils.byteArrayToBinaryString(bytes)
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })
    }


    private fun getPhotoActivityImage(str: String){
        photoViewModel.getPhotoFromDallE(str)
    }



}