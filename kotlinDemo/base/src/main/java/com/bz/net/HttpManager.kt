package com.bz.net

import android.annotation.SuppressLint
import com.blankj.utilcode.util.NetworkUtils
import com.bz.common.Api
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HttpManager {

    /**
     * get请求
     */
    @SuppressLint("CheckResult")
    fun  excuteGetString(url: String, params: MutableMap<String, Any>, responseCallBack: ResponseCallBack<String>) {
        if (!NetworkUtils.isConnected()) {
            responseCallBack.onFail("呀，不好啦！断网啦！")
        }
        ApiRetrofit.instance.initRetrofit()
                .create(Api::class.java)
                .executeGet(url, params)
                .compose(transFormer())
                .subscribe(StringCallBack(responseCallBack))
    }
    /**
     * get list请求
     */
    @SuppressLint("CheckResult")
    fun <T> excuteGetStringList(model: String, number: Int, page: Int,responseCallBack: ResponseCallBack<T>) {
        if (!NetworkUtils.isConnected()) {
            responseCallBack.onFail("呀，不好啦！断网啦！")
        }
        ApiRetrofit.instance.initRetrofit()
                .create(Api::class.java)
                .executeGetList(model, number,page)
                .compose(transFormer())
                .subscribe(BeanCallBack(responseCallBack))
    }
    /**
     * post请求
     */
    @SuppressLint("CheckResult")
    fun  excutePostString(url: String, params: MutableMap<String, Any>, responseCallBack: ResponseCallBack<String>) {
        if (!NetworkUtils.isConnected()) {
            responseCallBack.onFail("呀，不好啦！断网啦！")
        }
        ApiRetrofit.instance.initRetrofit()
                .create(Api::class.java)
                .executePost(url, params)
                .compose(transFormer())
                .subscribe(StringCallBack(responseCallBack))
    }

    /**
     * get请求  返回实体类
     */
    @SuppressLint("CheckResult")
    fun <T> excuteGetBean(url: String, params: MutableMap<String, Any>, responseCallBack: ResponseCallBack<T>) {
        if (!NetworkUtils.isConnected()) {
            responseCallBack.onFail("呀，不好啦！断网啦！")
        }
        ApiRetrofit.instance.initRetrofit()
                .create(Api::class.java)
                .executeGet(url, params)
                .compose(transFormer())
                .subscribe(BeanCallBack(responseCallBack))
    }

    /**
     * post请求  返回实体类
     */
    @SuppressLint("CheckResult")
    fun <T> excutePostBean(url: String, params: MutableMap<String, Any>, responseCallBack: ResponseCallBack<T>) {
        if (!NetworkUtils.isConnected()) {
            responseCallBack.onFail("呀，不好啦！断网啦！")
        }
        ApiRetrofit.instance.initRetrofit()
                .create(Api::class.java)
                .executePost(url, params)
                .compose(transFormer())
                .subscribe(BeanCallBack(responseCallBack))
    }

    fun <T> transFormer(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

