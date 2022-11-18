package com.example.kakaoimageai.presentation.view.fragment

import androidx.fragment.app.viewModels
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.FragmentPhotoCreateBinding
import com.example.kakaoimageai.presentation.view.base.BaseFragment
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel

class PhotoCreateFragment :
    BaseFragment<FragmentPhotoCreateBinding>(R.layout.fragment_photo_create) {

    private val photoViewModel: PhotoViewModel by viewModels()


}