package com.bz.kotlindemo.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bz.net.ApiRetrofit

object ImageLoadUtil {
    fun loadNetImg(context: Context,url:String,view: ImageView){
        Glide.with(context).load(url).into(view)
    }
}