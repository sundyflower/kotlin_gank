package com.bz.kotlindemo.adapter

import android.content.Context
import com.bz.kotlindemo.R
import com.bz.kotlindemo.util.ImageLoadUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class UsualAdapter(context: Context, layoutResId: Int, data: List<com.bz.kotlindemo.enty.Result>) : BaseQuickAdapter<com.bz.kotlindemo.enty.Result, BaseViewHolder>(layoutResId, data) {
    var context: Context? = null

    init {
        this.context = context
    }

    override fun convert(helper: BaseViewHolder?, item: com.bz.kotlindemo.enty.Result?) {
        if (item!!.images!=null && item!!.images.isNotEmpty()) {
            ImageLoadUtil.loadNetImg(context!!, item!!.images!![0], helper!!.getView(R.id.iv_them))
        }
        helper!!.setText(R.id.tv_title, item!!.desc)
        helper!!.setText(R.id.tv_time, item!!.createdAt.substring(0,10))
        helper!!.setText(R.id.tv_writer, item!!.who)

    }


}