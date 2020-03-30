package com.bruce.sx.ui.main.system.list

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.SystemListEntity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.system.list
 * @description:
 * @date: 2020/3/30
 * @time:  15:06
 */
interface SystemListContract {
    interface View:IBaseView{
        fun showList(list: MutableList<SystemListEntity>)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun loadData()
    }
}