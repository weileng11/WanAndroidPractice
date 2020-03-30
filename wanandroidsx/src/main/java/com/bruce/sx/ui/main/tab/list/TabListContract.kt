package com.bruce.sx.ui.main.tab.list

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.ArticleEntity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.tab.list
 * @description:
 * @date: 2020/3/27
 * @time:  17:37
 */
interface TabListContract {
    interface View: IBaseView {
        fun showList(list:MutableList<ArticleEntity.DatasBean>)
        fun collectSuccess()
        fun unCollectSuccess()
    }
    interface Presenter<T>: IBasePresenter<View> {
        fun loadData(type:Int,id:Int,pageNum:Int)
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}