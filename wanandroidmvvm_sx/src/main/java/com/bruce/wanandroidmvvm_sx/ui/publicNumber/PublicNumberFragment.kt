package com.bruce.wanandroidmvvm_sx.ui.publicNumber

import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.databinding.FragmentViewpagerBinding

/**
 * @author:
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.publicNumber
 * @description:
 * @date: 2020/4/29
 * @time:  15:57
 */
class PublicNumberFragment : BaseFragment<PublicNumberViewModel, FragmentViewpagerBinding>()  {

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