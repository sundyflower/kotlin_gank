package com.bz.kotlindemo.ui.mvp.usual

import com.bz.kotlindemo.enty.ModelBean
import com.bz.mvp.BaseView

interface UsualFragmentView :BaseView{
    fun getData(data:ModelBean)
}