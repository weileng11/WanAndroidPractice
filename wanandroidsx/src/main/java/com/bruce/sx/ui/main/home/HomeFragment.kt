package com.bruce.sx.ui.main.home

import com.bruce.sx.R
import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.LazyFragment

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.home
 * @description:首页
 * @date: 2020/3/26
 * @time:  16:38
 */
class HomeFragment:LazyFragment<IBasePresenter<*>>() {
    override fun lazyInit() {
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
}