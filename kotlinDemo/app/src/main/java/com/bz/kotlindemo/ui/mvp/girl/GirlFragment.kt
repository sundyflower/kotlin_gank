package com.bz.kotlindemo.ui.mvp.girl

import com.blankj.utilcode.util.LogUtils
import com.bz.base.BaseFragment
import com.bz.kotlindemo.R

class GirlFragment :BaseFragment(){
    override fun initLayout(): Int {
        return R.layout.fragmemt_girl_layout
    }

    override fun initView() {
        showContentView()
        LogUtils.dTag("girl","GirlFragment")
    }

    override fun initData() {

    }
}