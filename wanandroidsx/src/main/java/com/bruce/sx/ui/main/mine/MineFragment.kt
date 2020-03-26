package com.bruce.sx.ui.main.mine

import com.bruce.sx.R
import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.LazyFragment

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.mine
 * @description:个人信息
 * @date: 2020/3/26
 * @time:  16:38
 */
class MineFragment : LazyFragment<IBasePresenter<*>>() {
    override fun lazyInit() {
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }
}