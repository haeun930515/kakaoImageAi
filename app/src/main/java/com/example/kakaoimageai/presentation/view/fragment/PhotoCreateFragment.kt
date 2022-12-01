package com.example.kakaoimageai.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.FragmentPhotoCreateBinding
import com.example.kakaoimageai.presentation.view.base.BaseFragment
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_photo_create.*

@AndroidEntryPoint
class PhotoCreateFragment :
    BaseFragment<FragmentPhotoCreateBinding>(R.layout.fragment_photo_create) {

    private val photoViewModel: PhotoViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()



    }

    private fun init(){
        photoViewModel.result.observe(viewLifecycleOwner){
            binding.createdURL.text = it.data[0].url
        }
        binding.createBtn.setOnClickListener {
            photoViewModel.getPhotoFromDallE("kimchi")
        }
    }


}