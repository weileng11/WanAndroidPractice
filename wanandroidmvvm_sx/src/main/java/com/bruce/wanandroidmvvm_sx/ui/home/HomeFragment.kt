package com.bruce.wanandroidmvvm_sx.ui.home

import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.databinding.FragmentHomeBinding

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.home
 * @description:
 * @date: 2020/4/29
 * @time:  15:42
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(){

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
    }

    override fun lazyLoadData() {
    }

    override fun createObserver() {
    }
}