package com.bruce.wanandroid.base.mvp

import com.bruce.wanandroid.base.BaseActivity

/**
 * @author: BaseMVPActivity
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroid.base.mvp
 * @description:
 * @date: 2019/11/12
 * @time:  15:34
 */
abstract class BaseMVPActivity<in V : IView,P : IPresenter<in V>>: BaseActivity(),IView{
   protected lateinit var presenter: P

    override fun initData() {
        super.initData()
        presenter = createPresenter()
        presenter.attachView(this as V)
    }

    abstract fun createPresenter(): P
}