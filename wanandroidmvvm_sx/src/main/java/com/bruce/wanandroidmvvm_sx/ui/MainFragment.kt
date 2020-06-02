package com.bruce.wanandroidmvvm_sx.ui

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.app.ext.init
import com.bruce.wanandroidmvvm_sx.app.ext.setUiTheme
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.databinding.FragmentMainBinding
import com.bruce.wanandroidmvvm_sx.ui.home.HomeFragment
import com.bruce.wanandroidmvvm_sx.ui.me.MeFragment
import com.bruce.wanandroidmvvm_sx.ui.project.ProjectFragment
import com.bruce.wanandroidmvvm_sx.ui.publicNumber.PublicNumberFragment
import com.bruce.wanandroidmvvm_sx.ui.tree.TreeArrFragment
import kotlinx.android.synthetic.main.fragment_main.*
import me.hgj.jetpackmvvm.BaseViewModel
import me.hgj.jetpackmvvm.util.ActivityMessenger.finish

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui
 * @description:主页fg
 * @date: 2020/4/29
 * @time:  15:43
 */
class MainFragment : BaseFragment<BaseViewModel, FragmentMainBinding>() {

    var fragments = arrayListOf<Fragment>()
    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val projectFragment: ProjectFragment by lazy { ProjectFragment() }
    private val treeArrFragment: TreeArrFragment by lazy { TreeArrFragment() }
    private val publicNumberFragment: PublicNumberFragment by lazy { PublicNumberFragment() }
    private val meFragment: MeFragment by lazy { MeFragment() }

    init {
        fragments.apply {
            add(homeFragment)
            add(projectFragment)
            add(treeArrFragment)
            add(publicNumberFragment)
            add(meFragment)
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        //初始化viewpager2
        main_viewpager.init(this,fragments,false).run {
            offscreenPageLimit = fragments.size
        }
        //初始化 bottombar
        main_bottom.run {
            enableAnimation(false)
            enableShiftingMode(false)
            enableItemShiftingMode(false)
            appViewModel.appColor.value?.let {
                itemIconTintList = SettingUtil.getColorStateList(it)
                itemTextColor = SettingUtil.getColorStateList(it)
            }
            setTextSize(12F)
            setOnNavigationItemSelectedListener{
                when (it.itemId) {
                    R.id.menu_main -> main_viewpager.setCurrentItem(0, false)
                    R.id.menu_project -> main_viewpager.setCurrentItem(1, false)
                    R.id.menu_system -> main_viewpager.setCurrentItem(2, false)
                    R.id.menu_public -> main_viewpager.setCurrentItem(3, false)
                    R.id.menu_me -> main_viewpager.setCurrentItem(4, false)
                }
                true
            }
        }
    }

    override fun lazyLoadData() {
    }

    override fun createObserver() {
        appViewModel.appColor.observe(viewLifecycleOwner, Observer {
            setUiTheme(it, main_bottom)
        })
    }

}