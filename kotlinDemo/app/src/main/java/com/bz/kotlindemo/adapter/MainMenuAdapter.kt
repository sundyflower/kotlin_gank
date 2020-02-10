package com.bz.kotlindemo.adapter

import android.content.Context
import android.graphics.Color
import com.blankj.utilcode.util.LogUtils
import com.bz.kotlindemo.R
import com.bz.kotlindemo.enty.MenuBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class MainMenuAdapter(context: Context, layoutResId: Int, data: List<MenuBean>) : BaseQuickAdapter<MenuBean, BaseViewHolder>(layoutResId, data) {
    private var context: Context? = null
    private var position = -1

    fun setPosition(position: Int) {
        this.position = position
    }

    init {
        this.context = context
    }

    override fun convert(helper: BaseViewHolder, item: MenuBean) {

            helper.setImageResource(R.id.iv_icon, item.imgRes)
            helper.setText(R.id.tv_title, item.title)
            LogUtils.dTag("adapterposition", helper.adapterPosition)


//        helper!!.setImageResource(R.id.iv_icon, item!!.imgRes)
//        helper!!.setText(R.id.tv_title, item!!.title)


        if (position == helper.adapterPosition) {
            LogUtils.dTag("1111", "111111")
            helper.setBackgroundColor(R.id.ll_item, Color.BLUE)
        } else {
            helper.setBackgroundColor(R.id.ll_item, Color.WHITE)

        }
    }

}

