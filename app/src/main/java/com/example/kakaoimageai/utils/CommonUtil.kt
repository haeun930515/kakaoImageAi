package com.example.kakaoimageai.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.kakaoimageai.MyApplication
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream

object CommonUtil {

    val context = MyApplication.application

    const val FB_DB_URL = "https://kakaoimageai-default-rtdb.firebaseio.com"
    const val FB_DB_USERS = "users"
    const val FB_DB_IMGS = "images"
    const val FB_DB_USER_CHILD_ID = "id"

    val blist = mutableListOf("first")

    lateinit var FB_REF: DatabaseReference

    val database = Firebase.database(FB_DB_URL)

    /**
     * urlImgLoadGlide
     *      = Glide 를 사용하여 url 로부터 이미지를 생성하여 view 에 주입
     *
     *      parameter :
     *          - url - "String" 이미지를 가져올 url
     *          - view - "Image View" 이미지를 주입할 대상
     *          - context - "Context" 이 앱의 context
     *
     */

    fun urlImgLoadGlide(url: String, view: AppCompatImageView) {
        Glide.with(context)
            .load(url)
            .into(view)
    }

    /**
     * loadImgFromGlide
     *
     *
     *  Parameter :
     *      url - "Binary string" 이미지를 가져오는 url
     *      view - "Image View" xml에서 이미지를 생성할 뷰 아이템
     *      context - "Context" 앱의 context
     *
     */

    fun loadImgFromGlide(binary: String, view: AppCompatImageView) {
        val bdd = JavaUtils.binaryStringToByteArray(binary)
        val btm = BitmapFactory.decodeByteArray(bdd, 0, bdd.size)
        Glide.with(context)
            .asBitmap()
            .load(btm)
            .into(view)
    }



}