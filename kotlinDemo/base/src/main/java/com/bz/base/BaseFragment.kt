package com.bz.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bz.kotlindemo.base.BaseDialog
import com.bz.mvp.BasePresent
import kotlinx.android.synthetic.main.activity_base_layout.*
import kotlinx.android.synthetic.main.activity_error_view.*
import me.yokeyword.fragmentation.SupportFragment

abstract class BaseFragment:SupportFragment(){
    var baseDialog : BaseDialog?=null
    var errorView: View?=null
    var successView: View?=null
    var loadView: View?=null
    var baseView:View?=null




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        baseView= LayoutInflater.from(_mActivity).inflate(R.layout.fragment_base_layout,container,false);
        return baseView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
    protected fun show() {
        if (baseDialog == null) {
            baseDialog = _mActivity?.let { BaseDialog(it) }
            baseDialog!!.show()
        } else {
            baseDialog!!.show()
        }

    }



    /**
     * 显示成功后的的内容
     */
    protected fun showContentView(){
        fl_contain.removeAllViews()
        successView= View.inflate(_mActivity,initLayout(),null)
        fl_contain.addView(successView)
    }
    /**
     * 显示loading
     */
    protected fun showLoadingView(){
        fl_contain.removeAllViews()
        loadView= View.inflate(_mActivity,R.layout.activity_loading_view,null)
        fl_contain.addView(loadView)
    }
    /**
     * 显示错误页面
     */
    protected fun showErrorView(){
        fl_contain.removeAllViews()
        errorView= View.inflate(_mActivity,R.layout.activity_error_view,null)
        fl_contain.addView(errorView)
        ll_error.setOnClickListener { onRetry() }
    }
    /**
     * 返回ui
     */
    abstract fun initLayout(): Int

    abstract fun initView()

    abstract fun initData()
    /**
     * 点击重试
     */
    open fun onRetry() {}
}