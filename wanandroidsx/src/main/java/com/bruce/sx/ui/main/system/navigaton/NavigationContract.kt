package com.bruce.sx.ui.main.system.navigaton

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.NavigationEntity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.system.navigaton
 * @description:
 * @date: 2020/3/30
 * @time:  15:01
 */
interface NavigationContract {
    interface View: IBaseView {
        fun showList(list: MutableList<NavigationEntity>)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun loadData()
    }
}