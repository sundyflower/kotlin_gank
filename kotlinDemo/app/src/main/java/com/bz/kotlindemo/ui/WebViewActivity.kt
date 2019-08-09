package com.bz.kotlindemo.ui

import android.os.Build
import android.support.annotation.RequiresApi
import android.webkit.WebSettings
import android.webkit.WebView
import com.bz.kotlindemo.R
import com.bz.kotlindemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web_layout.*

class WebViewActivity :BaseActivity(){
    private var url=""
    private var webView:WebView?=null
    private var webSettings:WebSettings?=null
    override fun initLayout(): Int {
        return R.layout.activity_web_layout
    }

    override fun initView() {
        showContentView()
        setToolBarTitle("哎呀妈呀，笑skr人")
        webView= WebView(this)
        webSettings=webView!!.settings
        webSettings!!.javaScriptEnabled=true
        ll_web_group.removeAllViews()
        ll_web_group.addView(webView)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initData() {
        url=intent.getStringExtra("url")
        webView!!.loadUrl(url)

    }

    override fun onDestroy() {
        super.onDestroy()
        webSettings!!.javaScriptEnabled=false
        webView!!.removeAllViews()
        webView!!.destroy()
        ll_web_group.removeAllViews()


    }

}