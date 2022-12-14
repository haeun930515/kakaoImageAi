package com.example.kakaoimageai.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaoimageai.databinding.ItemPhotoBinding
import com.example.kakaoimageai.domain.entity.DallEImage
import com.example.kakaoimageai.presentation.view.base.BaseViewHolder
import kotlin.properties.Delegates

class PhotoAdapter(private val callback: PhotoCallback) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){

    var items: MutableList<DallEImage> by Delegates.observable(mutableListOf()) {_,_,_ ->
        notifyDataSetChanged()
    }

    interface PhotoCallback {

    }

    fun add(data: MutableList<DallEImage>){
        if(data.isNotEmpty()){
            items.addAll(data)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: ItemPhotoBinding)
        :BaseViewHolder<DallEImage>(binding){
        override fun define(item: DallEImage) {
            binding.txtImgUrl.text = item.data[0].url
        }

    }
}