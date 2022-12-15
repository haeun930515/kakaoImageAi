package com.example.kakaoimageai.presentation.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.FragmentSettingBinding
import com.example.kakaoimageai.presentation.view.base.BaseFragment
import com.example.kakaoimageai.presentation.viewmodel.UserInfoViewModel

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting){
    private val UserInfoViewModel : UserInfoViewModel by viewModels()

    override fun initView() {
        super.initView()
    }

    override fun initObserve() {
        UserInfoViewModel.userName.observe(viewLifecycleOwner){
            binding.txtUserName.text = it
        }
    }
}