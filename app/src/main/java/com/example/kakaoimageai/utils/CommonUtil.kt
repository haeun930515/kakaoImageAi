package com.example.kakaoimageai.utils

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference

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
}