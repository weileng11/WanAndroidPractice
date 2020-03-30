package com.bruce.sx.ui.main.tab

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.TabEntity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.tab
 * @description:
 * @date: 2020/3/27
 * @time:  17:21
 */
interface TabContract {
    interface View: IBaseView {
        fun showList(list:MutableList<TabEntity>)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun loadData(type:Int)
    }
}