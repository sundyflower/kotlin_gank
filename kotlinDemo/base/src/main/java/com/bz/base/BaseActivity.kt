package com.bz.kotlindemo.base

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.bz.base.R
import com.bz.mvp.BasePresent
import com.bz.mvp.BaseView
import kotlinx.android.synthetic.main.activity_base_layout.*
import kotlinx.android.synthetic.main.activity_error_view.*
import me.yokeyword.fragmentation.SupportActivity

abstract class BaseActivity : SupportActivity() {
    var baseDialog: BaseDialog? = null
    var errorView: View? = null
    var successView: View? = null
    var loadView: View? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_layout)

        initView()
        initData()
    }

    protected fun show() {
        if (baseDialog == null) {
            baseDialog = BaseDialog(this)
            baseDialog!!.show()
        } else {
            baseDialog!!.show()
        }

    }

    /**
     * 显示成功后的的内容
     */
    protected fun showContentView() {
        fl_contain.removeAllViews()
        successView = View.inflate(this, initLayout(), null)
        successView?.let { fl_contain.addView(successView) }

    }

    /**
     * 显示loading
     */
    protected fun showLoadingView() {
        fl_contain.removeAllViews()
        loadView = View.inflate(this, R.layout.activity_loading_view, null)
        loadView?.let { fl_contain.addView(loadView) }

    }

    /**
     * 显示错误页面
     */
    protected fun showErrorView() {
        fl_contain.removeAllViews()
        errorView = View.inflate(this, R.layout.activity_error_view, null)
        errorView?.let {
            fl_contain.addView(errorView)
            ll_error.setOnClickListener { onRetry() }
        }

    }



    fun getToolbar():Toolbar{
        return toolbar
    }
    fun setToolBarTitle(title:String){
        tv_title.setText(title)
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