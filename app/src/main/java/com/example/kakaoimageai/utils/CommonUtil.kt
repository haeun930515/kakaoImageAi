package com.example.kakaoimageai.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.database.DatabaseReference
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

    fun imageToByte(src: String, context: Context){
        Glide.with(context)
            .asBitmap()
            .load("https://oaidalleapiprodscus.blob.core.windows.net/private/org-SeWynDd17C9y4AtfAIkAlEU1/user-1Dg0ldfpaYTQUqHhULENQTyu/img-ZnM4qQOYkpdxuHqFO6DqKn6g.png?st=2022-12-16T01%3A29%3A56Z&se=2022-12-16T03%3A29%3A56Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2022-12-15T20%3A14%3A16Z&ske=2022-12-16T20%3A14%3A16Z&sks=b&skv=2021-08-06&sig=sBr9yhbW1QIdalexoha2rIQw9VInSEZLczr7AlI7nm8%3D")
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val baos = ByteArrayOutputStream()
                    resource.compress(Bitmap.CompressFormat.PNG, 100, baos)
                    val bytes = baos.toByteArray()
                    Log.d("BINARY", JavaUtils.byteArrayToBinaryString(bytes))
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun byteToBinaryString(byteArray: ByteArray): String {
        return String(byteArray)
    }
}