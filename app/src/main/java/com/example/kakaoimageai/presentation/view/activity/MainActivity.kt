package com.example.kakaoimageai.presentation.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.ActivityMainBinding
import com.example.kakaoimageai.presentation.view.base.BaseActivity
import com.example.kakaoimageai.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){

    private val mainViewModel : MainViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.apply {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_frame_layout) as NavHostFragment
            bottomNavigationView.setupWithNavController(navHostFragment.navController)
        }
    }

}