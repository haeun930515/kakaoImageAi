package com.example.kakaoimageai.presentation.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kakaoimageai.R
import com.example.kakaoimageai.databinding.FragmentSettingBinding
import com.example.kakaoimageai.presentation.view.base.BaseFragment
import com.example.kakaoimageai.presentation.view.base.BindingFragment
import com.example.kakaoimageai.presentation.viewmodel.UserInfoViewModel

class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting){

    override fun initObserve() {
        userInfoViewModel.userName.observe(viewLifecycleOwner){
            binding.txtUserName.text = it
        }
    }
}