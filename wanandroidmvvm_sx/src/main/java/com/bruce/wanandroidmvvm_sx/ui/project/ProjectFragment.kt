package com.bruce.wanandroidmvvm_sx.ui.project

import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.databinding.FragmentViewpagerBinding

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.project
 * @description:
 * @date: 2020/4/29
 * @time:  15:42
 */
class ProjectFragment: BaseFragment<ProjectViewModel, FragmentViewpagerBinding>()  {
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