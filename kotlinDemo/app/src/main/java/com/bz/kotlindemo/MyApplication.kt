package com.bz.kotlindemo

import android.content.Context
import com.bz.base.BaseApplication
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlin.properties.Delegates

class MyApplication : BaseApplication() {
    //静态的application类
    companion object {
        private var instance: MyApplication by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object: DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(context: Context, layout: RefreshLayout): RefreshHeader {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
                return WaterDropHeader(context)
            }

        })
        SmartRefreshLayout.setDefaultRefreshFooterCreator(object :DefaultRefreshFooterCreator{
            override fun createRefreshFooter(context: Context, layout: RefreshLayout): RefreshFooter {
                return  ClassicsFooter(context).setDrawableSize(20f)
            }

        })

    }

}