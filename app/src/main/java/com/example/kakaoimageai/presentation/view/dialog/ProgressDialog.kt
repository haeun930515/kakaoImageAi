package com.example.kakaoimageai.presentation.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.kakaoimageai.R

class ProgressDialog constructor(context: Context) : Dialog(context){

    init {
        setCanceledOnTouchOutside(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_progress)
    }
}