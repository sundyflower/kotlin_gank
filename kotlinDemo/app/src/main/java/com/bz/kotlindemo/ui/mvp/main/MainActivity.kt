package com.bz.kotlindemo.ui.mvp.main

import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.bz.kotlindemo.R
import com.bz.kotlindemo.adapter.MainMenuAdapter
import com.bz.kotlindemo.base.BaseActivity
import com.bz.kotlindemo.enty.MenuBean
import com.bz.kotlindemo.ui.mvp.usual.UsualFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var mainMenuAdapter: MainMenuAdapter? = null
    var arrayList: ArrayList<MenuBean>? = null
    var usualFragment: UsualFragment? = null
    var position = 0


    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        showContentView()
        initToolbar()

    }

    override fun initData() {
        usualFragment = UsualFragment.newInstance(1)
        loadRootFragment(R.id.dl_contain, usualFragment!!)
    }

    private fun initToolbar() {
        setToolBarTitle("Android")
        getToolbar().title=""
        setSupportActionBar(getToolbar())
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, getToolbar(), R.string.tool_open, R.string.tool_close)
        actionBarDrawerToggle!!.syncState()
        drawer_layout.addDrawerListener(actionBarDrawerToggle!!)
        menu_rv.layoutManager = LinearLayoutManager(this)
        arrayList = arrayListOf()
        arrayList!!.add(MenuBean(R.mipmap.qianduan_icon, "前端"))
        arrayList!!.add(MenuBean(R.mipmap.aicon, "Android"))
        arrayList!!.add(MenuBean(R.mipmap.ios_icon, "Ios"))
        arrayList!!.add(MenuBean(R.mipmap.tuozhan_icon, "拓展资源"))
        arrayList!!.add(MenuBean(R.mipmap.video_icon, "休息视频"))
        arrayList!!.add(MenuBean(R.mipmap.girl_icon, "妹子"))
        mainMenuAdapter = MainMenuAdapter(this, R.layout.item_menu_layout, arrayList!!)
        mainMenuAdapter!!.addHeaderView(View.inflate(this, R.layout.head_top_layout, null))
        menu_rv.adapter = mainMenuAdapter

        mainMenuAdapter!!.setOnItemClickListener { adapter,
                                                   view, position ->
            choiceFragment(position + 1)
//            LogUtils.dTag("position",position+1)
//            mainMenuAdapter!!.setPosition(position+1)
//            mainMenuAdapter!!.notifyDataSetChanged()
        }


    }


    /**
     * 根据不同的条目选择不同的fragment
     * */
    private fun choiceFragment(position: Int) {
        drawer_layout!!.closeDrawers()
        when (position) {
            1 -> {
                usualFragment!!.changeMoudle(1)
                setToolBarTitle("前端")
            }
            2 -> {
                usualFragment!!.changeMoudle(2)
                setToolBarTitle("Android")
            }
            3 -> {
                usualFragment!!.changeMoudle(3)
                setToolBarTitle("iOS")
            }
            4 -> {
                usualFragment!!.changeMoudle(4)
                setToolBarTitle("拓展资源")
            }
            5 -> {
                usualFragment!!.changeMoudle(5)
                setToolBarTitle("休息视频")
            }
            6 -> {
                ToastUtils.showShort("fuck every day")
                setToolBarTitle("妹子")
            }
        }
    }

}






