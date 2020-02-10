package com.bz.kotlindemo.ui.mvp.usual

import android.content.Intent
import com.bz.base.BaseFragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import com.blankj.utilcode.util.ToastUtils
import com.bz.kotlindemo.R
import com.bz.kotlindemo.adapter.UsualAdapter
import com.bz.kotlindemo.enty.ModelBean
import com.bz.kotlindemo.enty.Result
import com.bz.kotlindemo.ui.WebViewActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_usual_layout.*


class UsualFragment : BaseFragment(), UsualFragmentView, OnRefreshListener, OnLoadMoreListener {

    private val QD_MOUDLE = 1
    private val ANDROID_MODEL = 2
    private val IOS_MOUDLE = 3
    private val TZ_MOUDLE = 4
    private val VIDEO_MOUDLE = 5
    private val GIRl_MOUDLE = 6
    private var moudle = 0
    lateinit var present: UsualFragmentPresent
    lateinit var usualAdapter: UsualAdapter
    var page = 1
    var pageNumber = 10
    lateinit var arrayList: ArrayList<Result>


    companion object {
        fun newInstance(flag: Int): UsualFragment {
            val args = Bundle()
            val fragment = UsualFragment()
            args.putInt("flag", flag)
            fragment.arguments = args
            return fragment
        }
    }

    override fun initLayout(): Int {
        return R.layout.fragment_usual_layout

    }

    override fun initView() {
        showContentView()
        present = UsualFragmentPresent()
        present.attach(this)
        rv_usual.layoutManager = (LinearLayoutManager(_mActivity))
        refresh_layout.setOnRefreshListener(this)
        refresh_layout.setOnLoadMoreListener(this)

    }


    override fun initData() {
        moudle = arguments!!.getInt("flag", 0)
        refresh_layout.autoRefresh()
        arrayList = arrayListOf()

    }

    fun changeMoudle(moudle: Int) {
        this.moudle = moudle
        refresh_layout.autoRefresh()
    }

    private fun getModelList(mode: String, number: Int, page: Int) {
        present.getModelList(mode, number, page)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        present.detach()
    }

    override fun getData(data: ModelBean) {
        if (refresh_layout.state == RefreshState.Refreshing) {
            refresh_layout.finishRefresh()
            arrayList.clear()
            arrayList.apply {
                addAll(data.results)
            }

        } else if (refresh_layout.state == RefreshState.Loading) {
            refresh_layout.finishLoadMore()
            arrayList.apply {
                addAll(data.results)
            }
        }
        if (usualAdapter == null) {
            usualAdapter = UsualAdapter(_mActivity, R.layout.item_model_layout, arrayList!!)
            rv_usual.adapter = usualAdapter
        } else {
            usualAdapter!!.notifyDataSetChanged()
        }
        usualAdapter!!.setOnItemClickListener { adapter, view, position ->

            startActivity(Intent(activity, WebViewActivity::class.java).putExtra("url", data.results[position].url))
        }


    }

    override fun loadView() {

    }

    override fun hideView() {

    }

    override fun showErrorMsg(msg: String) {
        ToastUtils.showShort(msg)
        refresh_layout.finishRefresh()
        refresh_layout.finishLoadMore()
    }

    override fun onRetry() {
        super.onRetry()

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 1
        choiceModel()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        page++
        choiceModel()
    }

    private fun choiceModel() {
        when (moudle) {
            ANDROID_MODEL -> getModelList("Android", pageNumber, page)
            IOS_MOUDLE -> getModelList("iOS", pageNumber, page)
            TZ_MOUDLE -> getModelList("拓展资源", pageNumber, page)
            QD_MOUDLE -> getModelList("前端", pageNumber, page)
            VIDEO_MOUDLE -> getModelList("休息视频", pageNumber, page)
        }
    }

}