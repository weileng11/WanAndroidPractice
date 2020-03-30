package com.bruce.sx.ui.main.system

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bruce.sx.R
import com.bruce.sx.adapter.FragmentListAdapter
import com.bruce.sx.adapter.TabAdapter
import com.bruce.sx.base.BaseFragment
import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.LazyFragment
import com.bruce.sx.ui.main.system.list.SystemListFragment
import com.bruce.sx.ui.main.system.navigaton.NavigationFragment
import com.bruce.sx.weight.indicator.OnTabClickListener
import com.zs.wanandroid.constants.Constants
import kotlinx.android.synthetic.main.fragment_system.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.system
 * @description:体系
 * @date: 2020/3/26
 * @time:  16:39
 */
class SystemFragment : BaseFragment<IBasePresenter<*>>(), OnTabClickListener {

    private var tabList = mutableListOf<String>()

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun onTabClick(view: View, index: Int) {
        viewPager.currentItem = index
    }

    override fun init(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            llTop.elevation = 10f
        }
        tabList.add(Constants.SYSTEM_1)
        tabList.add(Constants.SYSTEM_2)
        initFragment()
    }

    private fun initFragment(){
        val fragmentList = mutableListOf<Fragment>()
        fragmentList.add(SystemListFragment())
        fragmentList.add(NavigationFragment())
        val adapter = FragmentListAdapter(fragmentList,childFragmentManager)
        viewPager.offscreenPageLimit = 6
        viewPager.adapter = adapter
        val commonNavigator = CommonNavigator(context)
        val tabAdapter = TabAdapter(tabList)
        //tab点击事件
        tabAdapter.setOnTabClickListener(this)
        commonNavigator.adapter = tabAdapter
        magicView.navigator = commonNavigator
        //将magicView和viewPager进行绑定
        ViewPagerHelper.bind(magicView,viewPager)
    }
}