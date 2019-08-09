package com.bz.net

import android.text.TextUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import org.json.JSONObject

class StringCallBack(responseCallBack: ResponseCallBack<String>): Observer<ResponseBody> {
    private  var responseCallBack: ResponseCallBack<String>
    private lateinit var disposable:Disposable
    init {
        this.responseCallBack=responseCallBack
    }

    override fun onComplete() {

        responseCallBack.onComplete()
        disposable.dispose()
    }

    override fun onSubscribe(d: Disposable) {
        disposable=d
    }

    override fun onNext(responseBody:  ResponseBody) {
        if (TextUtils.isEmpty(responseBody.string())) {
            var jsonObject:JSONObject= JSONObject(responseBody.string())
            if (!jsonObject.optBoolean("error")){
                responseCallBack.onSuccess(responseBody.string())
            }else{
                responseCallBack.onFail("加载错误")
            }
        }
    }

    override fun onError(e: Throwable) {
        responseCallBack.onFail(e.message.toString())
    }
}