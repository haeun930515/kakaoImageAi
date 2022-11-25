package com.example.kakaoimageai.domain.repository

import com.example.kakaoimageai.domain.entity.DallEImage
import retrofit2.Call

interface DallERepository {
    fun getDallEImageRetrofit(param: Map<String,String>): Call<DallEImage>
}