package com.bruce.wanandroidmvvm_sx.ui.tree

import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.databinding.FragmentViewpagerBinding

/**
 * @author:
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.tree
 * @description:
 * @date: 2020/4/29
 * @time:  15:57
 */
class TreeArrFragment : BaseFragment<TreeViewModel, FragmentViewpagerBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_viewpager
    }

    override fun initView() {
    }

    override fun lazyLoadData() {
    }

    override fun createObserver() {
    }
}