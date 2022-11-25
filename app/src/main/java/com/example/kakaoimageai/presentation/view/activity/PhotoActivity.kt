package com.example.kakaoimageai.presentation.view.activity

import androidx.activity.viewModels
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.ActivityPhotoBinding
import com.example.kakaoimageai.presentation.view.base.BaseActivity
import com.example.kakaoimageai.presentation.viewmodel.PhotoViewModel

class PhotoActivity : BaseActivity<ActivityPhotoBinding>(R.layout.activity_photo){

    private val photoViewModel : PhotoViewModel by viewModels()


}