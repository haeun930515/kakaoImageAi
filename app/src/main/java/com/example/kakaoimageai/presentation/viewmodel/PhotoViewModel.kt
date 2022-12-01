package com.example.kakaoimageai.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kakaoimageai.domain.entity.DallEImage
import com.example.kakaoimageai.domain.usecase.CallImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val callImageUseCase: CallImageUseCase
): ViewModel(){


    val result = MutableLiveData<DallEImage>()

    fun getPhotoFromDallE(text: String) {
        val param = HashMap<String,String>().apply {
            this["prompt"] = text
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

}