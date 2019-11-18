package com.bruce.wanandroid.main

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.BitmapFactory
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.bruce.wanandroid.R
import com.bruce.wanandroid.base.mvp.BaseMVPActivity
import com.bruce.wanandroid.common.annotation.EventBusSubscribe
import com.bruce.wanandroid.common.bean.FragmentItem
import com.bruce.wanandroid.db.bean.User
import com.bruce.wanandroid.favorite.FavoriteActivity
import com.bruce.wanandroid.gank.GankFragment
import com.bruce.wanandroid.home.HomeFragment
import com.bruce.wanandroid.main.adapter.MainViewPageAdapter
import com.bruce.wanandroid.main.bean.LoggedInEvent
import com.bruce.wanandroid.main.contract.MainContract
import com.bruce.wanandroid.main.presenter.MainPresenter
import com.bruce.wanandroid.main.widgets.MainViewPager
import com.bruce.wanandroid.meizi.MeiziActivity
import com.bruce.wanandroid.project.ProjectFragment
import com.bruce.wanandroid.search.SearchActivity
import com.bruce.wanandroid.setting.AboutActivity
import com.bruce.wanandroid.setting.SettingActivity
import com.bruce.wanandroid.system.SystemFragment
import com.bruce.wanandroid.user.activity.LoginActivity
import com.bruce.wanandroid.utils.blur
import com.bruce.wanandroid.utils.gotoActivity
import com.bruce.wanandroid.utils.isCookieNotEmpty
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.jaeger.library.StatusBarUtil
import com.xing.wanandroid.db.DbManager
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.system.exitProcess

/**
 * @author: MainActivity
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroid.main
 * @description:首页
 * @date: 2019/11/12
 * @time:  14:36
 */
@EventBusSubscribe
class MainActivity : BaseMVPActivity<MainContract.View,MainPresenter>(),MainContract.View, View.OnClickListener {

    private lateinit var mainMenu: ImageView
    private lateinit var mainSearch: ImageView
    private lateinit var mainTabLayout: TabLayout
    private lateinit var mainViewPager: MainViewPager
    private lateinit var mAdapter: MainViewPageAdapter
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var avatarBackground: ImageView
    private lateinit var usernameTextView: TextView
    private var loggedIn = false

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        drawerLayout = findViewById(R.id.dl_drawer_layout)
        StatusBarUtil.setColorForDrawerLayout(this, drawerLayout, resources.getColor(R.color.colorPrimary), 0)
        navigationView = this.findViewById(R.id.nv_left_navigation)
        //头部
        val headerView: View = navigationView.getHeaderView(0)
        usernameTextView = headerView.findViewById(R.id.tv_nav_username)
        avatarBackground = headerView.findViewById(R.id.iv_avatar_background)
        mainMenu = findViewById(R.id.iv_main_menu)
        mainSearch = findViewById(R.id.iv_main_search)
        mainTabLayout = findViewById(R.id.tl_main_tab)
        mainViewPager = findViewById(R.id.vp_main_pager)

        //打开抽屉
        mainMenu.setOnClickListener {
            openDrawer()
        }

        usernameTextView.setOnClickListener(this)

        //抽屉点击跳转
        navigationView.setNavigationItemSelectedListener { item ->
            closeDrawer()
            when (item.itemId) {
                R.id.item_nav_happy_minute -> {
                    gotoActivity(mContext as Activity, MeiziActivity().javaClass)
                }
                R.id.item_nav_favorite -> {
                    gotoActivity(mContext as Activity, FavoriteActivity().javaClass)
                }
                R.id.item_nav_setting -> {
                    gotoActivity(mContext as Activity, SettingActivity().javaClass)
                }
                R.id.item_nav_about -> {
                    gotoActivity(mContext as Activity, AboutActivity().javaClass)
                }
            }
            true
        }

        //模糊度
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.avatar)
        avatarBackground.setImageBitmap(blur(mContext, bitmap, 22))
    }

    override fun initData() {
        super.initData()
        //4个fragment
        val list = mutableListOf<FragmentItem>() //可变列表
        list.add(FragmentItem("首页", HomeFragment.newInstance()))
        list.add(FragmentItem("项目", ProjectFragment.newInstance()))
        list.add(FragmentItem("体系", SystemFragment.newInstance()))
        list.add(FragmentItem("干货", GankFragment.newInstance()))
        mAdapter = MainViewPageAdapter(this, supportFragmentManager, list)
        mainViewPager.adapter = mAdapter
        //tab 设置mainViewPager
        mainTabLayout.setupWithViewPager(mainViewPager)

        //循环添加tab
        for (i in 0..mainTabLayout.tabCount) {
            val tabView: TabLayout.Tab? = mainTabLayout.getTabAt(i)
            tabView?.customView = mAdapter.getTabView(i)
        }

        // 默认选中第 0 个
        mainViewPager.currentItem = 0
        //1.具体的tab 2.文字大小,3.是否选中
        changeTabView(mainTabLayout.getTabAt(0), 14f, true)

        mainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            //没有选中
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                changeTabView(tab, 14f, false)
            }
            //选中
            override fun onTabSelected(tab: TabLayout.Tab?) {
                changeTabView(tab, 14f, true)

            }
        })
        //是否登录
        setUsernameFromCache()
        //获取数据(db)
        presenter.getUserInfo()
        mainSearch.setOnClickListener(this)
    }

    private fun setUsernameFromCache() {
        //cokkie检测
        loggedIn = isCookieNotEmpty(mContext)
        if (!loggedIn) { //为空
            usernameTextView.text = getString(R.string.click_to_login)
        } else { //不为空
            val user = getCacheUser()
            val username: String
            if (user != null) {
                username = user.username
            } else {
                username = ""
            }
            usernameTextView.text = username
        }
    }

    /**
     * 设置用户名称，头像等信息
     */
    private fun setUsername(user: User?) {
        if (user != null) {
            usernameTextView.text = user.username
        } else {
            usernameTextView.text = getString(R.string.click_to_login)
        }
    }

    //缓存的数据库名称
    private fun getCacheUser(): User? {
        val users = DbManager.getInstance().getUserDao().loadAll()
        if (users != null && users.size > 0) {
            return users[0]
        }
        return null
    }

    //返回的数据
    override fun onUserInfo(user: User) {
        Log.e("MainActivity", user.toString())
        loggedIn = isCookieNotEmpty(mContext)
        if (loggedIn) {
            usernameTextView.text = user.username
        }
    }

    //登录后设置数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoginStatusChanged(event: LoggedInEvent) {
        val user = event.user
        setUsername(user)
    }

    private fun changeTabView(tab: TabLayout.Tab?, textSize: Float, isSelected: Boolean) {
        val view: View? = tab?.customView
        val textView: TextView? = view?.findViewById(R.id.tv_tab_title)
        textView?.textSize = textSize
        if (isSelected) {
            textView?.setTextColor(resources.getColor(android.R.color.holo_blue_bright))
            val width = textView?.measuredWidth
            Log.e("debug", "width = $width")
        } else {
            textView?.setTextColor(resources.getColor(android.R.color.black))
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //搜索
            R.id.iv_main_search -> {
                gotoSearchActivity()
                overridePendingTransition(0, 0)
            }
            //点击名称
            R.id.tv_nav_username -> {
                //登录后不跳转
                loggedIn = isCookieNotEmpty(mContext)
                if (!loggedIn) {
                    gotoLoginActivity()
                    closeDrawer()
                }
            }
        }
    }

    private fun gotoSearchActivity() {
        gotoActivity(this, SearchActivity().javaClass)
    }

    private fun gotoLoginActivity() {
        gotoActivity(this, LoginActivity().javaClass)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }


    /**
     * 打开抽屉
     */
    @SuppressLint("WrongConstant")
    private fun openDrawer() {
        drawerLayout.openDrawer(Gravity.START)
    }

    /**
     * 关闭抽屉
     */
    @SuppressLint("WrongConstant")
    private fun closeDrawer() {
        drawerLayout.closeDrawer(Gravity.START)
    }


    private var lastTime: Long = 0L

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val now = System.currentTimeMillis()
            if (now - lastTime > 1000) {
                Toast.makeText(mContext, "再按一次,推出应用", Toast.LENGTH_LONG).show()
                lastTime = now
                return false
            }
            finish()
            exitProcess(0)
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
