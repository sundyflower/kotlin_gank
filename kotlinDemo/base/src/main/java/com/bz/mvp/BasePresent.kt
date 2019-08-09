package com.bz.mvp

import com.bz.net.HttpManager

abstract  class BasePresent<T>{
    var view: T? = null
    var httpManager: HttpManager? = null


    fun attach(view: T) {
        this.view = view
        httpManager = HttpManager()
    }

    fun detach() {
        view = null
    }

}
