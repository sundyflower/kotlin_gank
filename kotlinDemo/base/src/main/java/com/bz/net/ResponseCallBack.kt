package com.bz.net

interface ResponseCallBack<T> {
    fun onSuccess(response:T)
    fun onFail(string: String)
    fun onComplete()
}