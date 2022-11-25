package com.example.kakaoimageai.data.repository

import com.example.kakaoimageai.data.remote.DallE
import com.example.kakaoimageai.domain.entity.DallEImage
import com.example.kakaoimageai.domain.repository.DallERepository
import retrofit2.Call
import javax.inject.Inject

class DallERepositoryImpl @Inject constructor(
    private val dallE: DallE
) : DallERepository {
    override fun getDallEImageRetrofit(param: Map<String, String>): Call<DallEImage> {
        return dallE.getImage(param)
    }
}