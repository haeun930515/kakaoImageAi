package com.example.kakaoimageai.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Base64
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.database.DatabaseReference
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object CommonUtil {

    const val FB_DB_URL = "https://kakaoimageai-default-rtdb.firebaseio.com"
    const val FB_DB_USERS = "users"
    const val FB_DB_USER_CHILD_ID = "id"
    lateinit var FB_REF: DatabaseReference

    fun urlImgLoadGlide(src: String, view: AppCompatImageView,context: Context){
        Glide.with(context)
            .load(src)
            .into(view)
    }

    fun imageToByte(src: String, context: Context): String{
        var result = ""
        Glide.with(context)
            .asBitmap()
            .load(src)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val baos = ByteArrayOutputStream()
                    resource.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val bytes = baos.toByteArray()
                    result = JavaUtils.byteArrayToBinaryString(bytes)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
        return result
    }

    fun loadImgFromGlide(str: String, context: Context, view: AppCompatImageView){
        val bdd = JavaUtils.binaryStringToByteArray(str)
        val btm = BitmapFactory.decodeByteArray(bdd,0,bdd.size)
        Glide.with(context)
            .asBitmap()
            .load(btm)
            .into(view)
    }
}