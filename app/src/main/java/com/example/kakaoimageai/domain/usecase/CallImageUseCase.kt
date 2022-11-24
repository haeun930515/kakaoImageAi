package com.example.kakaoimageai.domain.usecase

import com.example.kakaoimageai.domain.entity.DallEImage
import com.example.kakaoimageai.domain.repository.DallERepository
import retrofit2.Call
import javax.inject.Inject

class CallImageUseCase @Inject constructor(
    private val dallERepository: DallERepository
) {
    fun dallEImageGenerate(prompt: Map<String,String>): Call<DallEImage>{
        return dallERepository.getDallEImageRetrofit(prompt)
    }
}