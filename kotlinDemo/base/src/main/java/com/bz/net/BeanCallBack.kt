package com.bz.net

import android.text.TextUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import org.json.JSONObject
import java.lang.reflect.Type

class BeanCallBack<T>(responseCallBack: ResponseCallBack<T>) : Observer<ResponseBody> {
    private var responseCallBack: ResponseCallBack<T>
    private lateinit var disposable: Disposable
    private var type: Type? = null
    private var types: Array<Type>? = null

    init {
        this.responseCallBack = responseCallBack

    }

    override fun onComplete() {
        responseCallBack.onComplete()
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(responseBody: ResponseBody) {
        types = responseCallBack.javaClass.getGenericInterfaces()
        if (Utils.MethodHandler(types!!).size <1) {
            responseCallBack.onFail("获取实体类型失败")
            return
        }
        type = Utils.MethodHandler(types!!)[0]
        var str = responseBody.string()

//        var data = Utils.parseObject(str, BaseResponseBean::class.java)

        str.let {
            var jsonObject=JSONObject(str)
            if (!jsonObject.optBoolean("error")) {
//                var data = jsonObject.optString("results")
//                if (type === Utils.MethodHandler(String().javaClass.genericInterfaces)[0]) {
//                    responseCallBack.onSuccess(data!! as T)
//                    onComplete()
//                    return
//                }

                responseCallBack.onSuccess(Utils.parseObject(str, type!!) as T)
            }
        }



    }

    override fun onError(e: Throwable) {
        responseCallBack.onFail(e.message.toString())
    }

}