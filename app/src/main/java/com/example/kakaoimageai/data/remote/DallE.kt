package com.example.kakaoimageai.data.remote

import com.example.kakaoimageai.domain.entity.DallEImage
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface DallE {
    @FormUrlEncoded
    @POST("v1/images/generations")
    fun getImage(@QueryMap param: Map<String,String>): Call<DallEImage>
}