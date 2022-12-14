package com.example.kakaoimageai.presentation.view.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Type>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Type) {
        binding.executePendingBindings()
        define(item)
    }

    open fun define(item: Type){}
}