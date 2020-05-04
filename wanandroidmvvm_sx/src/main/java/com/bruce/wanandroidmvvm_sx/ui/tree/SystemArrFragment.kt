package com.bruce.wanandroidmvvm_sx.ui.tree

import android.view.Gravity
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.app.ext.bindViewPager2
import com.bruce.wanandroidmvvm_sx.app.ext.init
import com.bruce.wanandroidmvvm_sx.app.ext.initClose
import com.bruce.wanandroidmvvm_sx.data.bean.SystemResponse
import com.bruce.wanandroidmvvm_sx.databinding.FragmentSystemBinding
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlinx.android.synthetic.main.include_viewpager.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/3/4
 * 描述　:
 */
class SystemArrFragment : BaseFragment<TreeViewModel, FragmentSystemBinding>() {

    lateinit var data: SystemResponse
    var index = 0
    private var fragments: ArrayList<Fragment> = arrayListOf()

    override fun layoutId() = R.layout.fragment_system

    override fun initView() {
        arguments?.let {
            data = it.getSerializable("data") as SystemResponse
            index = it.getInt("index")
        }
        toolbar.initClose(data.name) {
            Navigation.findNavController(it).navigateUp()
        }
        //初始化时设置顶部主题颜色
        appViewModel.appColor.value?.let { viewpager_linear.setBackgroundColor(it) }
        //设置栏目标题居左显示
        (magic_indicator.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.LEFT

    }

    override fun lazyLoadData() {
        data.children.forEach {
            fragments.add(SystemChildFragment.newInstance(it.id))
        }
        //初始化viewpager2
        view_pager.init(this, fragments)
        //初始化 magic_indicator
        magic_indicator.bindViewPager2(view_pager, data.children)

        view_pager.offscreenPageLimit = fragments.size

        view_pager.postDelayed({
            view_pager.currentItem = index
        },100)

    }

    override fun createObserver() {

    }

}