package com.bruce.wanandroid.base.mvp

import com.bruce.wanandroid.base.BaseFragment

/**
 * @author: BaseMVPFragment
 * @project: BaseMVPFragment
 * @package: com.bruce.wanandroid.base.mvp
 * @description:
 * @date: 2019/11/12
 * @time:  15:34
 */
abstract class BaseMVPFragment<in V : IView, P : IPresenter<in V>> : BaseFragment(), IView {

    protected lateinit var presenter: P

    override fun initData() {
        presenter = createPresenter()
        presenter.attachView(this as V)
    }

    abstract fun createPresenter(): P


}