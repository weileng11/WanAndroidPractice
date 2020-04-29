package com.bruce.sx.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.bruce.sx.R
import com.bruce.sx.base.BaseActivity
import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.constants.Constants
import com.bruce.sx.ui.main.home.HomeFragment
import com.bruce.sx.ui.main.mine.MineFragment
import com.bruce.sx.ui.main.system.SystemFragment
import com.bruce.sx.ui.main.tab.TabFragment
import com.bruce.sx.utils.ToastUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main
 * @description:主页 （盛夏的果实）
 * @date: 2020/3/26
 * @time:  11:28
 */
class MainActivity : BaseActivity<IBasePresenter<*>>(), IBaseView {
    override fun showLoading() {
    }

    override fun closeLoading() {
    }

    private var lastIndex = 0
    private var fragments: MutableList<Fragment> = mutableListOf()

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init(savedInstanceState: Bundle?) {
        initFragment()
        initBottom()

//        LiveEventBus.get("key",String.class).observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                Log.i(TAG,s);
//            }
//        });

        LiveEventBus.get("key").observe(this, object :Observer<Any>{
            override fun onChanged(t: Any?) {
                Log.i("info","测试LiveEventBus"+t.toString())
                ToastUtils.show("ccccc")
            }

        })
    }

    private fun initBottom() {
        btmNavigation.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> setFragmentPosition(0)
                    R.id.menu_project -> setFragmentPosition(1)
                    R.id.menu_square -> setFragmentPosition(2)
                    R.id.menu_official_account -> setFragmentPosition(3)
                    R.id.menu_mine -> setFragmentPosition(4)
                }
                true
            }
        }
    }

    private fun initFragment() {
        //首页
        fragments.add(HomeFragment())
        //项目
        val project = TabFragment()
        val proBundle = Bundle()
        proBundle.putInt("type", Constants.PROJECT_TYPE)
        project.arguments = proBundle
        fragments.add(project)
        //体系
        fragments.add(SystemFragment())
        //公众号
        val account = TabFragment()
        val accountBundle = Bundle()
        accountBundle.putInt("type", Constants.ACCOUNT_TYPE)
        account.arguments = accountBundle
        fragments.add(account)
        //我的
        fragments.add(MineFragment())
        //设置默认显示第一个
        setFragmentPosition(0)
    }

    private fun setFragmentPosition(position: Int) {
        //ft事务
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val currentFragment: Fragment = fragments[position]
        //最后显示的item
        val lastFragment: Fragment = fragments[lastIndex]
        lastIndex = position
        //隐藏最后显示Item
        ft.hide(lastFragment)
        //如果正在被添加
        if (!currentFragment.isAdded) {
            //移除
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            //直接添加
            ft.add(R.id.frameLayout, currentFragment)
            //懒加载处理
            ft.setMaxLifecycle(currentFragment, Lifecycle.State.RESUMED)
        }
        ft.show(currentFragment)
        ft.commit()
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }


    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String) {
    }

    private var lastTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - this.lastTime > 2000L) {
            ToastUtils.show("再按一次退出程序")
            this.lastTime = System.currentTimeMillis()
            return
        } else {
            super.onBackPressed()
        }
    }

}
