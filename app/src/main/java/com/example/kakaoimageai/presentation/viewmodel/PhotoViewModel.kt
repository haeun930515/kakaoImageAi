package com.example.kakaoimageai.presentation.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.kakaoimageai.domain.entity.DallEImage
import com.example.kakaoimageai.domain.entity.FirebaseImage
import com.example.kakaoimageai.domain.usecase.CallImageUseCase
import com.example.kakaoimageai.utils.CommonUtil
import com.example.kakaoimageai.utils.JavaUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val callImageUseCase: CallImageUseCase
): ViewModel(){


    val result = MutableLiveData<DallEImage>()
    var photoList = MutableLiveData<MutableList<FirebaseImage>>()
    val bibi = MutableLiveData<String>()


    fun getPhotoFromDallE(text: String) {
        val param = HashMap<String,String>().apply {
            this["prompt"] = text
            this["size"] = "256x256"
        }
        callImageUseCase.dallEImageGenerate(param).enqueue(object : Callback<DallEImage>{
            override fun onResponse(call: Call<DallEImage>, response: Response<DallEImage>) {
                result.value = response.body()
                Log.d("RESULT",response.body().toString())
            }

            override fun onFailure(call: Call<DallEImage>, t: Throwable) {
                Log.d("ERROR",t.message.toString())
            }
        })
    }


    fun getImagesFromDB(userId: String) {

        CommonUtil.FB_REF = CommonUtil.database.getReference(CommonUtil.FB_DB_USERS).child(userId).child("images")

        CommonUtil.FB_REF.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<FirebaseImage>()
                snapshot.children.forEach {
                    val fig = it.value as Map<*, *>
                    val fimage = FirebaseImage(fig["name"].toString(),fig["binary"].toString())
                    list.add(fimage)
                }
                photoList.value = list
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun addImageDB(userId: String,name: String,binary : String){
        val image = FirebaseImage(name, binary)
        CommonUtil.FB_REF = CommonUtil.database.getReference(CommonUtil.FB_DB_USERS).child(userId).child("images")
        CommonUtil.FB_REF.push().setValue(image)
    }






}