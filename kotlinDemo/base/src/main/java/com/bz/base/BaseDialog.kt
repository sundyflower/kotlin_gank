package com.bz.kotlindemo.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.bz.base.R

class BaseDialog : Dialog {

    constructor(context: Context) : super(context, R.style.dialog_bg)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_dialog)
        //设置点击屏幕不消失
        setCanceledOnTouchOutside(false)
        //设置点击返回键不消失
        setCancelable(false)
    }


}