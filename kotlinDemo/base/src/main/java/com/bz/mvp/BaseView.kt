package com.bz.mvp

interface BaseView {
    fun loadView()
    fun hideView()
    fun showErrorMsg(msg:String)
}