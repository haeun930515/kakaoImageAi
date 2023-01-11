package com.example.kakaoimageai.presentation.view.activity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.ActivityMainBinding
import com.example.kakaoimageai.presentation.view.base.BaseActivity
import com.example.kakaoimageai.presentation.view.base.BindingActivity
import com.example.kakaoimageai.presentation.viewmodel.MainViewModel
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_frame_layout) as NavHostFragment
            bottomNavigationView.setupWithNavController(navHostFragment.navController)
        }
    }
}