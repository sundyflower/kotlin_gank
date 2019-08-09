package com.bz.kotlindemo.ui.mvp.usual

import com.blankj.utilcode.util.LogUtils
import com.bz.kotlindemo.enty.ModelBean
import com.bz.mvp.BasePresent
import com.bz.net.ResponseCallBack

class UsualFragmentPresent : BasePresent<UsualFragmentView>() {

    fun getModelList(mode:String,number:Int,page:Int){
        view!!.loadView()
        httpManager!!.excuteGetStringList(mode,number,page,object : ResponseCallBack<ModelBean>{
            override fun onSuccess(response: ModelBean) {
                LogUtils.dTag("view","view=="+"运行打这里了success")
                if(view==null){
                    LogUtils.dTag("view","view=="+null)
                }else{
                    view!!.getData(response)
                }


            }

            override fun onFail(string: String) {
                LogUtils.dTag("view","view=="+"运行打这里了fail")
                view!!.showErrorMsg(string)
            }

            override fun onComplete() {

            }

        })

    }
}