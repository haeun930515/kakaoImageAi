package com.example.kakaoimageai.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaoimageai.databinding.ItemPhotoBinding
import com.example.kakaoimageai.domain.entity.DallEImage
import com.example.kakaoimageai.domain.entity.FirebaseImage
import com.example.kakaoimageai.presentation.view.base.BaseViewHolder
import com.example.kakaoimageai.utils.CommonUtil
import kotlin.properties.Delegates

class PhotoAdapter(private val callback: PhotoCallback) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){

    var items: MutableList<FirebaseImage> by Delegates.observable(mutableListOf()) {_,_,_ ->
        notifyDataSetChanged()
    }

    interface PhotoCallback {

    }

    fun add(data: MutableList<FirebaseImage>){
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
        :BaseViewHolder<FirebaseImage>(binding){
        override fun define(item: FirebaseImage) {
            if(!item.binary.isNullOrEmpty()) {
                CommonUtil.loadImgFromGlide(item.binary!!, binding.itemImg)
            }
            binding.txtImgName.text = item.name
        }

    }
}