package com.bruce.sx.ui.main.tab

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bruce.sx.R
import com.bruce.sx.adapter.FragmentListAdapter
import com.bruce.sx.adapter.TabAdapter
import com.bruce.sx.base.BaseFragment
import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.LazyFragment
import com.bruce.sx.entity.TabEntity
import com.bruce.sx.ui.main.tab.list.TabListFragment
import com.bruce.sx.utils.ToastUtils
import com.bruce.sx.weight.indicator.OnTabClickListener
import kotlinx.android.synthetic.main.fragment_project.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.tab
 * @description:项目和公众号
 * @date: 2020/3/26
 * @time:  16:39
 */
class TabFragment : BaseFragment<TabContract.Presenter<TabContract.View>>()
    ,TabContract.View ,OnTabClickListener{
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var tabList = mutableListOf<TabEntity>()
    private var type:Int? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun init(savedInstanceState: Bundle?) {
        //接收type
        type = arguments?.getInt("type") //10 20
        Log.i(TAG,"type:$type")
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            flTop.elevation = 10f
        }
        type?.let { presenter?.loadData(it) }
    }

    override fun onTabClick(view: View, index: Int) {
        viewPager.currentItem = index
    }

    override fun showList(list: MutableList<TabEntity>) {
        tabList.clear()
        tabList.addAll(list)
        initFragment()
    }

    private fun initFragment() {
        val fragmentList = mutableListOf<Fragment>()
        val list = mutableListOf<String>()
        tabList.forEach {
            val fragment = TabListFragment()
            val bundle = Bundle()
            //type id name
            type?.let { it1 -> bundle.putInt("type", it1) }
            bundle.putInt("id", it.id)
            bundle.putString("name", it.name)
            fragment.arguments = bundle
            fragmentList.add(fragment)
            //名称
            it.name?.let { it1 -> list.add(it1) }
        }
        //适配
        val adapter = FragmentListAdapter(fragmentList,childFragmentManager)
        viewPager.offscreenPageLimit = 6
        viewPager.adapter = adapter
        val commonNavigator = CommonNavigator(context)
        //设置头部sp
        val tabAdapter = TabAdapter(list)
        //tab点击事件
        tabAdapter.setOnTabClickListener(this)
        commonNavigator.adapter = tabAdapter
        magicView.navigator = commonNavigator
        //将magicView和viewPager进行绑定
        ViewPagerHelper.bind(magicView,viewPager)
    }

    override fun onError(error: String) {
        ToastUtils.show(error)
    }

    override fun createPresenter(): TabContract.Presenter<TabContract.View>? {
        return TabPresenter(this)
    }

}