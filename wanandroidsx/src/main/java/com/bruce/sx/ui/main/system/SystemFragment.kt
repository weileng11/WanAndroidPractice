package com.bruce.sx.ui.main.system

import com.bruce.sx.R
import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.LazyFragment

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.system
 * @description:公众号
 * @date: 2020/3/26
 * @time:  16:39
 */
class SystemFragment : LazyFragment<IBasePresenter<*>>() {
    override fun lazyInit() {
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }
}